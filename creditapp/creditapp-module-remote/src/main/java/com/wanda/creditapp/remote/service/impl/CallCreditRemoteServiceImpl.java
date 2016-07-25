 package com.wanda.creditapp.remote.service.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.util.Base64Util;
import com.wanda.creditapp.common.util.DESUtil;
import com.wanda.creditapp.common.util.DeEnCodeUtil;
import com.wanda.creditapp.common.util.JsonUtil;
import com.wanda.creditapp.common.util.RSAUtil;
import com.wanda.creditapp.common.util.StringUtils;
import com.wanda.creditapp.remote.service.ICallCreditRemoteService;
import com.wanda.creditapp.remote.util.RequestHelper;

/**
 * 调用远程征信业务系统,待业务网关上线后,需要切换为调用业务网关
 * @author xuxiaobin5
 *
 */
@Service("callCreditRemoteService")
public class CallCreditRemoteServiceImpl implements ICallCreditRemoteService{
	
	@Value("${gate_url}")
	private String url;
	
	@Value("${acct_id}")
	private String acct_id;
	
	@Value("${api_key}")
	private String api_key;
	
	@Value("${cer_file_path}")
	private String cer_file_path;
	
	@Value("${product_path}")
	private String product_path;
	
	@Value("${token_path}")
	private String token_path;
	
	private static final Logger log = Logger.getLogger(CallCreditRemoteServiceImpl.class);

	@Override
	public Map<String, Object> callRemoteCredit(Map<String, String> params, Map<String, String> datas) throws CreditAppException{
		Map<String,String> requestParams = buildAuthParams();
		try {
			requestParams.put("requestBody", DeEnCodeUtil.encodeDataByPublicKey(JsonUtil.buildNonNullBinder().toJson(datas), cer_file_path));
		} catch (Exception e) {
			log.error("encode requestBody occur an exception:",e);
			throw new CreditAppException(ExceptionConstant.innerException.getErrorCode(),ExceptionConstant.innerException.getErrorMessage());
		}
		Map<String,Object> result = doCall(requestParams,product_path,HttpPost.METHOD_NAME);
		result.put(ProductConstant.prod_id, datas.get(ProductConstant.prod_id));
		return result;
	}
	
	private Map<String,String> buildAuthParams(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("acct_id", acct_id);
		params.put("api_id", api_key);
		params.put("token_id", fetchToken(acct_id, api_key));

		params.put("request_sn", generateRequestSN());
		params.put("signature",sign(params.get("request_sn"), acct_id, api_key, params.get("token_id")));
		return params;
	}
	
	private String fetchToken(String acctId,String apiKey){
		Map<String, String> params = new HashMap<>();
		params.put("acct_id", acctId);
		params.put("api_id", apiKey);
		String requestSN = generateRequestSN();
		params.put("request_sn", requestSN);
		params.put("signature", sign(requestSN, acctId, apiKey));

		Map<String, Object> receiveMap = doCall(params, token_path, HttpGet.METHOD_NAME);
		if(ProductConstant.deal_success.equals(receiveMap.get(ProductConstant.retcode))){
			Map<String, Object> data = (Map<String, Object>) receiveMap.get("retdata");
			return (String)data.get("access_token");
		}else{
			log.error("fetch token fail:" + receiveMap.get(ProductConstant.retcode));
			throw new CreditAppException(ExceptionConstant.fetchTokenFail.getErrorCode(),ExceptionConstant.fetchTokenFail.getErrorMessage());
		}
	}
	
	private String sign(String... params){
		try {
			StringBuffer sb = new StringBuffer();
			for (String param : params) {
				sb.append(param);
			}
			String full_str = sb.toString() + new SimpleDateFormat("yyyyMMdd").format(new Date());
			PublicKey pk = DeEnCodeUtil.readPublicKey(cer_file_path);
			String encodedValue = null;
			if (pk != null) {
				byte[] encodedData = DESUtil.encrypt(full_str.getBytes(),
						new SimpleDateFormat("yyyyMMdd").format(new Date()));
				encodedData = RSAUtil.encryptByPublicKey(encodedData, Base64Util.encode(pk.getEncoded()));
				encodedValue = new String(encodedData, "ISO-8859-1");
			}
			return encodedValue;
		} catch (Exception ex) {
			log.info("Generate digital signature failed!");
			log.error(ex.getMessage(), ex.getCause());
			throw new CreditAppException(ExceptionConstant.innerException);
		}
	}
	
	private Map<String, Object> doCall(Map<String, String> params, String url, String requestMethod) {
		try {
			if (StringUtils.isNullOrEmpty(requestMethod)) {
				requestMethod = HttpGet.METHOD_NAME;
			}
			String res = "";
			if (HttpGet.METHOD_NAME.equals(requestMethod)) {
				res = RequestHelper.doGet(url, params);
			} else if (HttpPost.METHOD_NAME.equals(requestMethod)) {
				res = RequestHelper.doPost(url, params, true);

			}

			log.info("原始返回数据: " + new String(res.getBytes()));
			Map<String, Object> map = (Map<String,Object>)JsonUtil.buildNonNullBinder().getJsonToMap(res, String.class, Object.class);
			String retCode = map.get("retcode").toString();
			if ("000000".equals(retCode)) {
				try {
					// retdata 为加密节点
					String decodeResp = DeEnCodeUtil.decodeDataByPublicKey(map.get("retdata").toString(),
							cer_file_path);
					log.info("解密后返回数据:" + decodeResp);
					map.put("retdata",JsonUtil.buildNonNullBinder().getJsonToMap(decodeResp, String.class, Object.class));
					log.info("解析后返回数据:" + JsonUtil.buildNonNullBinder().toJson(map));
				} catch (Exception ex) {
					log.info("decode response retdata failed!");
					log.error(ex.getMessage(), ex.getCause());
					throw new CreditAppException(ExceptionConstant.innerException);
				}
			}
			return map;
		} catch (IOException | KeyManagementException | NoSuchAlgorithmException ex) {
			log.info("Invoke Remote Credit authentication failed!");
			log.error(ex.getMessage(), ex.getCause());
			throw new CreditAppException(ExceptionConstant.innerException);
		}
	}
	
	private String generateRequestSN() {
		SecureRandom random = new SecureRandom();
		// generate a random number
		StringBuffer request_sn = new StringBuffer(Long.toString(System.currentTimeMillis()));
		request_sn.append(random.nextInt()).append(random.nextInt());
		// max length = 32 ?
		if (request_sn.length() > 32) {
			return request_sn.substring(0, 32);
		}
		return request_sn.toString();
	}
	
	/**
	 * 在远程服务返回非000000code后,本系统包装的返回错误信息
	 * @param response
	 * @return
	 */
	private Map<String,Object> buildFailMap(Map<String,Object> response){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(ProductConstant.retcode, "-1");
		result.put(ProductConstant.retmsg, "调用远程服务失败");
		return result;
	}

}
