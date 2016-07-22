package com.wanda.creditapp.controller.verifycode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanda.creditapp.common.cache.RedisCache;
import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.controller.BaseController;
import com.wanda.creditapp.common.requestmodel.user.VerifyCodeModel;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.msg.common.MessageParam;
import com.wanda.creditapp.msg.common.MsgConstant;
import com.wanda.creditapp.msg.domain.VerifyCode;
import com.wanda.creditapp.msg.domain.VerifyConfig;
import com.wanda.creditapp.msg.service.IMsgDetailService;
import com.wanda.creditapp.msg.service.IVerifyCodeService;
import com.wanda.creditapp.msg.service.IVerifyConfigService;

@Controller
public class VerifyCodeController extends BaseController {
	private static final Logger logger = Logger.getLogger(VerifyCodeController.class);
	private static final int DEFAULT_EXPIRY_TIME = 30 * 60 * 1000;// 毫秒
	@Autowired
	IVerifyConfigService verifyConfigService;
	@Autowired
	IVerifyCodeService verifyCodeService;
	@Autowired
	IMsgDetailService msgDetailService;
	@Autowired
	RedisCache redisCache;

	@RequestMapping(value="/greenLight/verifycode",method=RequestMethod.POST)
	@ResponseBody
	public DataResponse verifyCode(@RequestBody VerifyCodeModel verifyCodeModel,HttpServletRequest request, HttpServletResponse response) {
		VerifyConfig verifyConfig = verifyConfigService.getByVerifyType(Integer.valueOf(verifyCodeModel.getType()));
		if (null == verifyConfig) {
			logger.error("VerifyCodeController.verifyCode-没有对应的配置信息");
			return new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}
		if (MsgConstant.VERIFYCODE_TYPE_1==verifyConfig.getVerifyType()){
			return smsVerifyCode(verifyConfig, verifyCodeModel.getMobileNo());
		} else if (MsgConstant.VERIFYCODE_TYPE_3==verifyConfig.getVerifyType()) {
			return picVerifyCode(verifyConfig, request, response);
		} else {
			return new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}

	}

	public DataResponse picVerifyCode(VerifyConfig verifyConfig, HttpServletRequest request, HttpServletResponse response) {
		String picVerifyCode = RandomStringUtils.random(verifyConfig.getVerifyLength(), verifyConfig.getVerifyChararray());
		request.getSession().setAttribute("picVerifyCode", picVerifyCode);
		// 禁止浏览缓存随机图片
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// 通知客户机以图片方式打开发送过去的数据
		response.setHeader("Content-Type", "image/jpeg");
		// 生成随机码
		// String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 在内存中创建一张图片
		BufferedImage image;
		try {
			image = VerifyCodeUtils.outputImage(100, 40, picVerifyCode);
			// 以response流的形式输出图片
			ImageIO.write(image, "jpg", response.getOutputStream());
		} catch (IOException e) {
			logger.error("VerifyCodeController.picVerifyCode-写出图片验证码失败", e);
			return new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}
		return new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
	}

	/**
	 * 短信验证码
	 * 
	 * @param verifyConfig
	 * @param mobileNo
	 * @return
	 */
	public DataResponse smsVerifyCode(VerifyConfig verifyConfig, String mobileNo) {
		VerifyCode verifyCode = buildVerifyCode(verifyConfig, mobileNo);
		try {
			Pattern pattern = Pattern.compile("(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}");
			Matcher matcher = pattern.matcher(mobileNo);
			if (!matcher.matches()) {
				logger.error("发送短信验证码,手机号校验不通过!");
				return new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_INPUTERROR);
			}
			// 注册并非高频业务，没必要入缓存
			// redisCache.setStrWithExpiry(CommonConstant.VERIFY_CODE_KEY +
			// verifyConfig.getVerifyType() + mobileNo,
			// verifyCode.getVerifyCode(), DEFAULT_EXPIRY_TIME / 1000);
			verifyCodeService.insert(verifyCode);
			// 发送短信验证码
			MessageParam messageParam = new MessageParam(verifyCode.getVerifyReceiver());

			msgDetailService.insertMsgDetail(verifyConfig.getTemplateCode(), verifyCode, messageParam);
			DataResponse dr = new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
			dr.put("ttl", "100");
			return dr;
		} catch (Exception e) {
			return new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}

	}

	/**
	 * 构造验证码对象
	 * 
	 * @param msgConfig
	 * @return
	 */
	private VerifyCode buildVerifyCode(VerifyConfig verifyConfig, String verifyReceiver) {
		VerifyCode vc = new VerifyCode();
		String randomCode = RandomStringUtils.random(verifyConfig.getVerifyLength(), verifyConfig.getVerifyChararray());
		vc.setVerifyReceiver(verifyReceiver);
		vc.setVerifyType(verifyConfig.getVerifyType());
		vc.setExpiredTime(new Date(System.currentTimeMillis() + DEFAULT_EXPIRY_TIME));
		vc.setVerifyCode(randomCode);
		return vc;
	}

}
