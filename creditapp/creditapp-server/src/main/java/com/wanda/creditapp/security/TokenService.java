package com.wanda.creditapp.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wanda.creditapp.common.cache.RedisCache;
import com.wanda.creditapp.common.constant.CommonConstant;
import com.wanda.creditapp.common.sercurity.User;
import com.wanda.creditapp.common.util.UUIDUtil;

public class TokenService {
	@Autowired
	RedisCache redisCache;

	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

	public static final int ACCESSTOKEN_VALID_DATE = 600;
	public static final int REFRESHTOKEN_VALID_DATE = 1800;
	public static final int ACCESSUSER_VALID_DATE = 1800;

	/**
	 * 给指定User生成AccessToken和RefreshToken并添加至redis
	 * @param user
	 */
	public void generateNewToken(final User user) {
		if (null == user || null == user.getUapPwid()) {
			logger.error("TokenService.generateNewToken-user或uapPwid为空！");
			return;
		}
		User userInCache = redisCache.getObj(CommonConstant.ACCESS_TOKEN_KEY+user.getUapPwid(),User.class);
		
		if (userInCache != null){
			logger.debug("old_userInCache:" + userInCache.getUapPwid());
			redisCache.delete(CommonConstant.ACCESS_USER_KEY + user.getUapPwid());
			redisCache.delete(CommonConstant.ACCESS_TOKEN_KEY+user.getAccessToken());
			redisCache.delete(CommonConstant.REFRESH_TOKEN_KEY+user.getRefreshToken());
		}
		user.setAccessToken(UUIDUtil.getUuid());
		user.setRefreshToken(UUIDUtil.getUuid());
		redisCache.setObjWithExpiry(CommonConstant.ACCESS_USER_KEY + user.getUapPwid(), user,ACCESSUSER_VALID_DATE);//根据用户pwdid
		redisCache.setObjWithExpiry(CommonConstant.ACCESS_TOKEN_KEY+user.getAccessToken(), user,ACCESSTOKEN_VALID_DATE);//根据accessToken
		redisCache.setObjWithExpiry(CommonConstant.REFRESH_TOKEN_KEY+user.getRefreshToken(), user,REFRESHTOKEN_VALID_DATE);//根据refreshToken
	}
	/**
	 * 根据accessToken获取User
	 * @param accessToken
	 * @return
	 */
	public User getUserByAccessToken(String accessToken) {
		User user=null;
		user= redisCache.getObj(CommonConstant.ACCESS_TOKEN_KEY+accessToken, User.class);
		return user;
	}
	/**
	 * 根据refreshToken获取User
	 * @param refreshToken
	 * @return
	 */
	public User getUserByRefreshToken(String refreshToken) {
		User user=null;
		user= redisCache.getObj(CommonConstant.REFRESH_TOKEN_KEY+refreshToken, User.class);
		return user;
	}
	/**
	 * 根据pwid获取User
	 * @param uapPwid
	 * @return
	 */
	public User getUserByUapPwid(String uapPwid) {
		User user=null;
		user= redisCache.getObj(CommonConstant.ACCESS_USER_KEY+uapPwid, User.class);
		return user;
	}

	// protected void storeInDb(String token, Integer userId) {
	// // set expire time = current time() + 1 month
	// UserAccessLog accessLog = userAccessLogMapper.findUserAccessLog(userId);
	// boolean insertMode = false;
	// if (accessLog == null) {
	// accessLog = new UserAccessLog();
	// insertMode = true;
	// }
	// accessLog.setUserId(userId);
	// accessLog.setRefreshToken(token);
	// accessLog.setExpiredTime(DateUtils.addDays(new Date(), 30));
	// accessLog.setCreatedTime(new Date());
	//
	// if (insertMode) {
	// userAccessLogMapper.insert(accessLog);
	// } else {
	// userAccessLogMapper.updateByPrimaryKeySelective(accessLog);
	// }
	// }

	//
	// public Authentication update(String token, User user) {
	// if (token == null || user == null) {
	// logger.warn("Cannot update empty token and user info");
	// return null;
	// }
	// AuthenticationWithToken authenticationWithToken = new
	// AuthenticationWithToken(user, null,
	// AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DOMAIN_USER"));
	// authenticationWithToken.setAccessToken(token);
	// user.setAccessToken(token);
	// valueOps.set(token, authenticationWithToken, HALF_AN_HOUR,
	// TimeUnit.MINUTES);
	// return authenticationWithToken;
	// }
	//
	// public void logoutUser(Integer userId) {
	// if (userId != null) {
	// UserAccessLog accessLog = userAccessLogMapper.findUserAccessLog(userId);
	// if (accessLog != null) {
	// userAccessLogMapper.deleteByPrimaryKey(accessLog.getId());
	// if (accessLog.getAccessToken() != null) {
	// redisTemplate.delete(accessLog.getAccessToken());
	// }
	// }
	// }
	// }
}
