package com.wanda.creditapp.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import com.wanda.creditapp.common.constant.CommonConstant;
import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.common.sercurity.GreenLightVerdict;
import com.wanda.creditapp.common.sercurity.User;
import com.wanda.creditapp.common.util.JsonUtil;
import com.wanda.creditapp.common.util.StringUtil;
import com.wanda.creditapp.security.TokenService;

public class AuthenticationFilter extends GenericFilterBean {

	private final static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

	TokenService tokenService;// 存取token

	GreenLightVerdict greenLightVerdict;// 裁定访问路径是否放行

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (verdictPath(httpRequest)) {// 如果是不需要权限校验的请求直接放行
			chain.doFilter(request, response);
		} else {
			User user = this.getUser(httpRequest);
			if (user == null) {
				DataResponse dataResponse = new DataResponse(ResponseConstant.RESPONSE_ACCESSTOKEN_INVALID);
				String jsonStr = JsonUtil.buildNormalBinder().toJson(dataResponse);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println(jsonStr);
				out.flush();
				out.close();
				return;
			}
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);
		}
	}

	/**
	 * 根据httpRequest的accessToken从redis中获取用户信息
	 * 
	 * @param httpRequest
	 * @return
	 */
	private User getUser(HttpServletRequest httpRequest) {
		User user = null;
		if (tokenService == null) {
			ServletContext sc = httpRequest.getSession().getServletContext();
			XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
			if (cxt != null && cxt.getBean("tokenService") != null)
				this.tokenService = (TokenService) cxt.getBean("tokenService");
		}
		String accessToken = this.getAccessToken(httpRequest);
		logger.debug("AuthenticationFilter-getUserByAccessToken[accessToken]: " + accessToken);
		user = tokenService.getUserByAccessToken(accessToken);
		return user;
	}

	/**
	 * 从httpRequest获取accessToken
	 * 
	 * @param httpRequest
	 * @return
	 */
	private String getAccessToken(HttpServletRequest httpRequest) {
		String accessToken = null;
		accessToken = httpRequest.getHeader(CommonConstant.ACCESS_TOKEN_KEY);
		if (StringUtil.isBlank(accessToken)) {
			Cookie[] cookies = httpRequest.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie c = cookies[i];
					if (c.getName().equalsIgnoreCase(CommonConstant.ACCESS_TOKEN_KEY)) {
						accessToken = c.getValue();
						break;
					}
				}
			}
		}
		return accessToken;
	}

	/**
	 * 判断请求路径是否可以放行
	 * 
	 * @param httpRequest
	 * @return
	 */
	private boolean verdictPath(HttpServletRequest httpRequest) {
		String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);
		if (greenLightVerdict == null) {
			ServletContext sc = httpRequest.getSession().getServletContext();
			XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
			if (cxt != null && cxt.getBean("greenLightVerdict") != null)
				this.greenLightVerdict = (GreenLightVerdict) cxt.getBean("greenLightVerdict");
		}
		return greenLightVerdict.greenLight(resourcePath);
	}

}
