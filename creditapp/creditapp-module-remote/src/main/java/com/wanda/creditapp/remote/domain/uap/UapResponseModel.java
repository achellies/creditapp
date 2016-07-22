package com.wanda.creditapp.remote.domain.uap;

import java.util.TreeMap;

import com.wanda.creditapp.remote.common.UapRemoteConstant;

public class UapResponseModel extends TreeMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	

	public UapResponseModel() {
		this.put(UapRemoteConstant.UAP_RESP_HEADER_KEY, new RespHeader());
	}

	// {
	// "PWID": 712929531927322624,
	// "respHeader": {
	// "reqId": "8272wweuudssw",
	// "respCode": "UAP-10000",
	// "respMessage": "调用成功",
	// "sessionId": "registersdf"
	// }
	// }

	// {\"PWID\": 712929531927322624,\"respHeader\": {\"reqId\":
	// \"8272wweuudssw\",\"respCode\": \"UAP-10000\",\"respMessage\":
	// \"调用成功\",\"sessionId\": \"registersdf\"}}
//	public static void main(String[] args) {
//		Map<String, Object> u = (Map<String, Object>) JsonUtil.buildNormalBinder().getJsonToMap("{\"PWID\": 712929531927322624,\"respHeader\": {\"reqId\": \"8272wweuudssw\",\"respCode\": \"UAP-10000\",\"respMessage\": \"调用成功\",\"sessionId\": \"registersdf\"}}", String.class, Object.class);
//		System.out.println(u.get("respHeader"));
//	}

	public RespHeader getRespHeader() {
		return (RespHeader) this.get(UapRemoteConstant.UAP_RESP_HEADER_KEY);
	}

	public void setRespHeader(RespHeader respHeader) {
		this.put(UapRemoteConstant.UAP_RESP_HEADER_KEY, respHeader);
	}

	public String getReqId() {
		return ((RespHeader) this.get(UapRemoteConstant.UAP_RESP_HEADER_KEY)).getReqId();
	}

	public void setReqId(String reqId) {
		((RespHeader) this.get(UapRemoteConstant.UAP_RESP_HEADER_KEY)).setReqId(reqId);
	}

	public String getRespCode() {
		return ((RespHeader) this.get(UapRemoteConstant.UAP_RESP_HEADER_KEY)).getRespCode();
	}

	public void setRespCode(String respCode) {
		((RespHeader) this.get(UapRemoteConstant.UAP_RESP_HEADER_KEY)).setRespCode(respCode);
	}

	public String getRespMessage() {
		return ((RespHeader) this.get(UapRemoteConstant.UAP_RESP_HEADER_KEY)).getRespMessage();
	}

	public void setRespMessage(String respMessage) {
		((RespHeader) this.get(UapRemoteConstant.UAP_RESP_HEADER_KEY)).setRespMessage(respMessage);
	}

	public String getSessionId() {
		return ((RespHeader) this.get(UapRemoteConstant.UAP_RESP_HEADER_KEY)).getSessionId();
	}

	public void setSessionId(String sessionId) {
		((RespHeader) this.get(UapRemoteConstant.UAP_RESP_HEADER_KEY)).setSessionId(sessionId);
	}

}
