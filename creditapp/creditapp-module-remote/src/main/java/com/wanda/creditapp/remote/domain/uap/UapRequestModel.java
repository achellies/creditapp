package com.wanda.creditapp.remote.domain.uap;

import java.util.TreeMap;

import com.wanda.creditapp.remote.common.UapRemoteConstant;

public class UapRequestModel extends TreeMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public UapRequestModel() {
		this.put(UapRemoteConstant.UAP_REQ_HEADER_KEY, new ReqHeader());
		this.put(UapRemoteConstant.UAP_LOG_COLLECTION_KEY, new LogCollection());
	}

	public ReqHeader getReqHeader() {
		return (ReqHeader) this.get(UapRemoteConstant.UAP_REQ_HEADER_KEY);
	}

	public void setReqHeader(ReqHeader reqHeader) {
		this.put(UapRemoteConstant.UAP_REQ_HEADER_KEY, reqHeader);
	}

	public LogCollection getLogCollection() {
		return (LogCollection) this.get(UapRemoteConstant.UAP_LOG_COLLECTION_KEY);
	}

	public void setLogCollection(LogCollection logCollection) {
		this.put(UapRemoteConstant.UAP_LOG_COLLECTION_KEY, logCollection);
	}

}
