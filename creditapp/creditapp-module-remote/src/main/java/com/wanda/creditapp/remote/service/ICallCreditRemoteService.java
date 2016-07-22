package com.wanda.creditapp.remote.service;

import java.util.Map;

/**
 * 调用远程接口服务
 * @author xuxiaobin5
 *
 */
public interface ICallCreditRemoteService {
	
	/**
	 * 
	 * @param params 非业务参数,根据业务网关的文档,主要有req_sn,inf_id,prod_id,acct_id,ext_data.
	 * @param datas 业务参数 业务参数需要转换成json格式,最终需要的格式为
	 * {"req_sn":"1234567","prod_id":"Prod1 ",” inf_id”:” P_C_B008”,"req_data":{"name":"张三","cardNo":"44538198010078999"}}
	 * @return 
	 */
	public Map<String,Object> callRemoteCredit(Map<String,String> params,Map<String,String> datas);
}

