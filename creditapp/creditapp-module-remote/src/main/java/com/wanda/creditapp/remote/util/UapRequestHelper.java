package com.wanda.creditapp.remote.util;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.util.JsonUtil;
import com.wanda.creditapp.common.util.StringUtil;
import com.wanda.creditapp.common.util.UUIDUtil;
import com.wanda.creditapp.remote.common.UapRemoteConstant;
import com.wanda.creditapp.remote.domain.uap.ReqHeader;
import com.wanda.creditapp.remote.domain.uap.UapRequestModel;
import com.wanda.creditapp.remote.domain.uap.UapResponseModel;

public class UapRequestHelper {
	private static final Logger logger = Logger.getLogger(RequestHelper.class);
	@Value("${uniPlatformUrl}")
	private String uniPlatformUrl;
	@Value("${uapAccessToken}")
	private String uapAccessToken;
	@Value("${uapAppId}")
	private String uapAppId;
	@Value("${uapEntityId}")
	private String uapEntityId;
	@Value("${uapReqId}")
	private String uapReqId;
	@Value("${uapSessionId}")
	private String uapSessionId;

	public UapResponseModel callUapPlatform(String apiPath, UapRequestModel uapRequestModel) {
		
		buildReqHeader(uapRequestModel);
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		
		headers.setContentType(type);
		
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		
		String requestBody = JsonUtil.buildNormalBinder().toJson(uapRequestModel);
		
		logger.info("请求参数：" + requestBody);
		
		HttpEntity<String> formEntity = new HttpEntity<String>(requestBody, headers);
		
		String result;
		
		try {
			
			result = restTemplate.postForObject(uniPlatformUrl + apiPath, formEntity, String.class);
			
			logger.info("响应结果：" + result);
			
		} catch (RestClientException e) {
			logger.error("调用统一账户平台异常", e);
			throw new CreditAppException("调用统一账户平台异常", e);
		}
		
		UapResponseModel uapResponseModel = convertJsonToUapResponseModel(result);
		
		return uapResponseModel;
	}

	/**
	 * 设置请求头信息
	 * 
	 * @param uapRequestParam
	 */
	private void buildReqHeader(UapRequestModel uapRequestModel) {
		ReqHeader rh = (ReqHeader) uapRequestModel.get(UapRemoteConstant.UAP_REQ_HEADER_KEY);
		rh.setAccessToken(uapAccessToken);
		rh.setAppId(uapAppId);
		rh.setEntityId(uapEntityId);
		rh.setSessionId(UUIDUtil.getUuid());
		rh.setReqId(UUIDUtil.getUuid());
	}

	/**
	 * 转换json to UapResponseModel
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private UapResponseModel convertJsonToUapResponseModel(String jsonStr) {
		if (StringUtil.isBlank(jsonStr))
			return null;
		UapResponseModel uapResponseModel = new UapResponseModel();
		Map<String, Object> map1 = (Map<String, Object>) JsonUtil.buildNormalBinder().getJsonToMap(jsonStr, String.class, Object.class);
	
		for (String key : map1.keySet()) {
			
			if (UapRemoteConstant.UAP_RESP_HEADER_KEY.equals(key)) {
				
				System.out.println(String.valueOf(map1.get(key)));
				
				Map<String, Object> map2 = (Map<String, Object>) map1.get(key);
				
				uapResponseModel.setRespCode(String.valueOf(map2.get("respCode")));
				uapResponseModel.setRespMessage(String.valueOf(map2.get("respMessage")));
				uapResponseModel.setSessionId(String.valueOf(map2.get("sessionId")));
				uapResponseModel.setReqId(String.valueOf(map2.get("reqId")));
				
			} else {
				uapResponseModel.put(key, map1.get(key));
			}
		}
		
		return uapResponseModel;
	}

}
