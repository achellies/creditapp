package com.wanda.creditapp.msg.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.util.UUIDUtil;
import com.wanda.creditapp.msg.common.MessageParam;
import com.wanda.creditapp.msg.common.MsgConstant;
import com.wanda.creditapp.msg.dao.MsgDetailMapper;
import com.wanda.creditapp.msg.dao.MsgTemplateMapper;
import com.wanda.creditapp.msg.dao.TsmMessagesMapper;
import com.wanda.creditapp.msg.domain.MsgDetail;
import com.wanda.creditapp.msg.domain.MsgTemplate;
import com.wanda.creditapp.msg.domain.TsmMessages;
import com.wanda.creditapp.msg.domain.VerifyCode;
import com.wanda.creditapp.msg.service.IMsgDetailService;
@Service("msgDetailService")
public class MsgDetailServiceImpl implements IMsgDetailService {
	private static final Logger logger = Logger.getLogger(MsgDetailServiceImpl.class);

	@Resource
	MsgTemplateMapper msgTemplateMapper;

	@Resource
	TsmMessagesMapper tsmMessagesMapper;

	@Resource
	MsgDetailMapper msgDetailMapper;

	@Override
	public void insertMsgDetail(String templateCode, VerifyCode verifyCode, MessageParam messageParam) {
		if (null == templateCode || null == verifyCode) {
			logger.error("MsgServiceImpl.sendSmsVerifyCode:msgConfig为空！");
			throw new CreditAppException(ExceptionConstant.errorullPointer);
		}
		MsgTemplate msgTemplate = msgTemplateMapper.selectByPrimaryKey(Integer.parseInt(templateCode));
		try {
			MsgDetail md = sendMsgWithVerifyCode(msgTemplate, verifyCode, messageParam);
			msgDetailMapper.insert(md);
		} catch (Exception e) {
			logger.error("TsmMessagesMapper.sendSmsVerifyCode-发送msg异常", e);
			throw new CreditAppException(e);
		}

	}

	private MsgDetail sendMsgWithVerifyCode(MsgTemplate msgTemplate, VerifyCode verifyCode, MessageParam messageParam) {
		MsgDetail md = new MsgDetail();
		TsmMessages tsm = new TsmMessages();
		String content = "";
		if (verifyCode != null) {
			messageParam.put(MsgConstant.VERIFYCODE_KEY, verifyCode.getVerifyCode());
			messageParam.put(MsgConstant.VERIFYCODEID_KEY, verifyCode.getId().toString());
			messageParam.put(MsgConstant.MSGRECEIVER_KEY, verifyCode.getVerifyReceiver());
		}
		try {
			content = replaceParam(msgTemplate.getMsgTemplate(), messageParam);
			if (msgTemplate != null & msgTemplate.getMsgType() == 2) {// 2是短信。由于短信不在平台内发送，所以做此校验
				tsm.setMessageid(UUIDUtil.getUuid());
				tsm.setTarget(verifyCode.getVerifyReceiver());
				tsm.setTitle(msgTemplate.getMsgTitle());
				tsm.setContent(content);
				tsm.setPriority(1);
				tsm.setMessagetype(2);
				tsm.setStatus(0);
				tsmMessagesMapper.insert(tsm);
			}
		} catch (Exception e) {
			logger.error("MsgServiceImpl.sendMsg-发送msg异常", e);
			throw new CreditAppException(e);
		}
		md.setMsgContent(content);
		md.setMsgReceiver(messageParam.get(MsgConstant.MSGRECEIVER_KEY));
		md.setMsgTitle(msgTemplate.getMsgTitle());
		md.setMsgType(msgTemplate.getMsgType());
		md.setVerifyCodeId(messageParam.get(MsgConstant.VERIFYCODEID_KEY));
		return md;
	}

	/**
	 * 不使用验证码发消息
	 * @param msgTemplate
	 * @param messageParam
	 * @return
	 */
	@SuppressWarnings("unused")
	private MsgDetail sendMsg(MsgTemplate msgTemplate, MessageParam messageParam) {
		return sendMsgWithVerifyCode(msgTemplate, null, messageParam);
	}

	public String replaceParam(String content, Map<String, String> params) {
		for (String key : params.keySet()) {
			content = content.replaceAll("\\$\\$" + key + "\\$\\$", params.get(key));
		}
		return content;
	}
}
