package com.wanda.creditapp.remote.util;

import java.util.Map;

import com.wanda.creditapp.common.util.StringUtils;

public class ValidateUtil {
	
	public static final String validateProductModel(Map<String,String> param,String...validateParams){
		for(String validateParam:validateParams){
			if(StringUtils.isNullOrEmpty(param.get(validateParam))){
				return "missing parameter:"+validateParam;
			}
		}
		return null;
	}

}
