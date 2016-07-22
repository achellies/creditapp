package com.wanda.creditapp.msg.common;

import java.util.HashMap;

public class MessageParam extends HashMap<String, String> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param msgRseceiver 消息接收者
	 */
	public MessageParam(String msgRseceiver) {
		this.put(MsgConstant.MSGRECEIVER_KEY, msgRseceiver);
	}
}
