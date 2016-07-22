package com.wanda.creditapp.remote.domain.uap;

public class ReqHeader {
	// entityId String Y 20 调用子公司ID
	private String entityId;

	// appId String Y 30 接入方系统编号。新接入时，由统一账户平台分发。
	private String appId;

	// 使用UUID
	private String sessionId;

	// reqId String Y 50 请求的流水号，唯一, 使用UUID。
	private String reqId;

	// accessToken String N 64 接入方系统的访问授权token，用于接入方系统的身份校验。在接入方接入时，由统一账户平台分发
	private String accessToken;

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
