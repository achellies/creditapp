package com.wanda.creditapp.user.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wanda.creditapp.common.sercurity.User;

public class UserContext {
	
	public static User getCurrentUser(){
		UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication.getPrincipal() == null) {
			return null;
		} else {
			return (User) (authentication.getPrincipal());
		}
	}

}
