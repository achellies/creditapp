package com.wanda.creditapp.controller.user;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanda.creditapp.common.cache.RedisCache;
import com.wanda.creditapp.common.constant.CommonConstant;
import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.controller.BaseController;
import com.wanda.creditapp.common.requestmodel.user.LoginModel;
import com.wanda.creditapp.common.requestmodel.user.ResetPwdByPasswordModel;
import com.wanda.creditapp.common.requestmodel.user.ResetPwdByUserPhoneModel;
import com.wanda.creditapp.common.requestmodel.user.UserRegisterModel;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.common.sercurity.User;
import com.wanda.creditapp.common.util.BeanUtil;
import com.wanda.creditapp.msg.common.MsgConstant;
import com.wanda.creditapp.msg.service.IVerifyCodeService;
import com.wanda.creditapp.remote.domain.uap.UapResponseModel;
import com.wanda.creditapp.remote.service.IUapUserManageService;
import com.wanda.creditapp.security.TokenService;
import com.wanda.creditapp.user.domain.UserDomain;
import com.wanda.creditapp.user.service.IUserService;
import com.wanda.creditapp.user.util.UserContext;

/**
 * 用户相关操作
 */
@Controller
public class UserController extends BaseController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private IUapUserManageService uapUserManageService;

	@Autowired
	private IVerifyCodeService verifyCodeService; // 验证码

	@Autowired
	private TokenService tokenService;

	@Autowired
	private RedisCache redisCache;

	/**
	 * 用户注册
	 */
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	@ResponseBody
	public DataResponse userRegister(@Validated @RequestBody UserRegisterModel userRegisterModel, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
		// 校验时的错误信息
		if (bindingResult.hasErrors())
			return buildRspWithErrors(bindingResult);
		// 判断手机号是否已经注册过
		Boolean flag = this.userService.queryUserPhoneByPhone(userRegisterModel.getUserPhone());
		if (flag) {
			return new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_PHONE_EXIST);
		}

		// 验证码错误
		if (!verifyCodeService.checkVerifyCode(userRegisterModel.getUserPhone(), userRegisterModel.getVerifyCode(), MsgConstant.VERIFYCODE_TYPE_1)) {
			return new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_VERIFYCODE_FAIL);
		}

		// 两次密码输入不一样+
		if (!userRegisterModel.getUserPassword().equals(userRegisterModel.getRePassWord())) {
			return new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_PWD_FAIL);
		}
		UserDomain userDomain = new UserDomain();
		// 响应模型
		UapResponseModel uapResponseModel = this.uapUserManageService.userRegister(userRegisterModel);
		// 判断返回结果
		if (CommonConstant.UAP_SUCCESS_CODE.equals(uapResponseModel.getRespCode())) {
			// 给实体类赋值
			userDomain.setUserPhone(userRegisterModel.getUserPhone());
			userDomain.setUapPwid(uapResponseModel.get(CommonConstant.PWID) + "");
			userDomain.setUserPassword(userRegisterModel.getUserPassword());
			int result = this.userService.insert(userDomain);
			if (result != 1) {
				return new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_NATIVE_FAIL);
			}
		} else {
			return new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}
		return produceToken(userDomain, request, response);
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@ResponseBody
	public DataResponse login(@Validated @RequestBody LoginModel loginModel, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
		// 校验时的错误信息
		if (bindingResult.hasErrors())
			return buildRspWithErrors(bindingResult);
		// // 判断验证码输入结果
		// if (!verifyCodeService.checkVerifyCode(loginModel.getUserPhone(),
		// loginModel.getVerifyCode())) {
		// return new
		// DataResponse(ResponseConstant.RESPONSE_ERRORCODE_VERIFYCODE_FAIL);
		// }
		// 查询用户是否在本地存在
		UserDomain userDomain = new UserDomain();
		userDomain.setUserPhone(loginModel.getUserPhone());
		userDomain.setUserPassword(loginModel.getUserPassword());
		UserDomain user = this.userService.queryUserByPhoneAndPwd(userDomain);
		if (null == user) {
			return new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_NATIVE_FAIL);
		}
		// 响应模型
		UapResponseModel uapResponseModel = this.uapUserManageService.login(loginModel);
		// 判断返回结果
		if (!CommonConstant.UAP_SUCCESS_CODE.equals(uapResponseModel.getRespCode())) {
			return new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}
		userDomain.setUapPwid(uapResponseModel.get(CommonConstant.PWID) + "");
		return produceToken(userDomain, request, response);
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/user/refreshToken", method = RequestMethod.POST)
	@ResponseBody
	public DataResponse refreshToken(@Validated @RequestBody Map<String,String> aaa, HttpServletRequest request, HttpServletResponse response) {
		User user = tokenService.getUserByRefreshToken(aaa.get("refreshToken"));
		if (null == user) {
			return new DataResponse(ResponseConstant.RESPONSE_REFRESHTOKEN_INVALID);
		}
		return produceToken(user, request, response);
	}

	/**
	 * 通过短信验证码途径，重置登录密码
	 */
	@RequestMapping(value = "/user/resetPwdByPhone", method = RequestMethod.POST)
	@ResponseBody
	public Object resetPwdByPhone(@Validated @RequestBody ResetPwdByUserPhoneModel resetPwdByUserPhoneModel, BindingResult bindingResult) {
		// 校验时的错误信息
		if (bindingResult.hasErrors())
			return buildRspWithErrors(bindingResult);
		// 验证码错误
		if (!verifyCodeService.checkVerifyCode(resetPwdByUserPhoneModel.getUserPhone(), resetPwdByUserPhoneModel.getVerifyCode(), MsgConstant.VERIFYCODE_TYPE_2)) {
			return new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_VERIFYCODE_FAIL);
		}
		// 响应模型
		UapResponseModel uapResponseModel = this.uapUserManageService.resetPwdByPhone(resetPwdByUserPhoneModel);

		// 判断返回结果
		if (CommonConstant.UAP_SUCCESS_CODE.equals(uapResponseModel.getRespCode())) {
			// 根据手机号更新密码
			UserDomain userDomain = new UserDomain();
			userDomain.setUserPhone(resetPwdByUserPhoneModel.getUserPhone());
			userDomain.setUapPwid(uapResponseModel.get(CommonConstant.PWID) + "");
			userDomain.setUserPassword(resetPwdByUserPhoneModel.getUserPassword());
			int result = this.userService.updatePwdByPhone(userDomain);
			if (result == -1) {
				return new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_NATIVE_FAIL);
			}
			// 清除缓存中的用户
			this.redisCache.delete(CommonConstant.ACCESS_USER_KEY + uapResponseModel.get(CommonConstant.PWID));
		} else {
			// 响应失败
			return new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}
		return new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
	}

	/**
	 * 通过原登录密码,重置登录密码
	 */
	@RequestMapping(value = "/user/resetPwdByPwd", method = RequestMethod.POST)
	@ResponseBody
	public Object resetPwdByPwd(@Validated @RequestBody ResetPwdByPasswordModel resetPwdByPasswordModel, BindingResult bindingResult) {
		// 校验时的错误信息
		if (bindingResult.hasErrors())
			return buildRspWithErrors(bindingResult);
		// 输入的原登录密码是否正确
		String pwd = this.userService.queryOldPwdByPwid(resetPwdByPasswordModel.getPWID());
		if (!resetPwdByPasswordModel.getOldPwd().equals(pwd)) {
			return new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_OLDPWD_FAIL);
		}
		// 响应模型
		UapResponseModel uapResponseModel = this.uapUserManageService.resetPwdByPwd(resetPwdByPasswordModel);
		// 判断返回结果
		if (CommonConstant.UAP_SUCCESS_CODE.equals(uapResponseModel.getRespCode())) {
			// 重置密码
			UserDomain userDomain = new UserDomain();
			userDomain.setUapPwid(uapResponseModel.get(CommonConstant.PWID) + "");
			userDomain.setUserPassword(resetPwdByPasswordModel.getUserPassword());
			int result = this.userService.updateUserPwdByPwd(userDomain);
			if (result == -1) {
				return new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_NATIVE_FAIL);
			}
			// 清除缓存中的用户
			this.redisCache.delete(CommonConstant.ACCESS_USER_KEY + uapResponseModel.get(CommonConstant.PWID));
		} else {
			// 响应失败
			return new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}
		return new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
	}

	/**
	 * 根据UserDomain生成token
	 * 
	 * @param userDomain
	 * @param request
	 * @param response
	 * @return
	 */
	private DataResponse produceToken(UserDomain userDomain, HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		try {
			BeanUtil.copyAllPropertys(user, userDomain);
		} catch (Exception e) {
			logger.error("UserController.userRegister-构造User对象并写入redis失败", e);
			return null;
		}
		// 设置token并且添加到redis
		tokenService.generateNewToken(user);
		Cookie cookie = new Cookie(CommonConstant.ACCESS_TOKEN_KEY, user.getAccessToken());
		cookie.setPath(request.getContextPath() + "/");
		cookie.setMaxAge(600);// 有效期10分钟
		response.addCookie(cookie);
		// 响应成功
		DataResponse dr = new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
		dr.put(CommonConstant.ACCESS_TOKEN_KEY, user.getAccessToken());
		dr.put(CommonConstant.REFRESH_TOKEN_KEY, user.getRefreshToken());
		dr.put(CommonConstant.PWID, user.getUapPwid());
		return dr;
	}
	
	@RequestMapping(value="/user/queryUserInfo")
	@ResponseBody
	public DataResponse queryUserInfo(){
		DataResponse response = new DataResponse();
		User user = UserContext.getCurrentUser();
		if(user==null){
			response.setResultCode(ResponseConstant.RESPONSE_FAIL.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_FAIL.getMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORCODE_KEY, ExceptionConstant.fetchLoginUserFail.getErrorCode());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORMESSAGE_KEY, ExceptionConstant.fetchLoginUserFail.getErrorMessage());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORDISPLAY_KEY, 1);
			return response;
		}
		response.setResultCode(ResponseConstant.RESPONSE_SUCCESS.getCode());
		response.setResultMessage(ResponseConstant.RESPONSE_SUCCESS.getMsg());
		response.getDataMap().put("name", user.getUserRelname());
		response.getDataMap().put("cardNo", user.getUserIdNumber());
		return response;
	}

	private DataResponse produceToken(User user, HttpServletRequest request, HttpServletResponse response) {
		// 设置token并且添加到redis
		tokenService.generateNewToken(user);
		Cookie cookie = new Cookie(CommonConstant.ACCESS_TOKEN_KEY, user.getAccessToken());
		cookie.setPath(request.getContextPath() + "/");
		cookie.setMaxAge(600);// 有效期10分钟
		response.addCookie(cookie);
		// 响应成功
		DataResponse dr = new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
		dr.put(CommonConstant.ACCESS_TOKEN_KEY, user.getAccessToken());
		dr.put(CommonConstant.REFRESH_TOKEN_KEY, user.getRefreshToken());
		dr.put(CommonConstant.PWID, user.getUapPwid());
		return dr;
	}

}
