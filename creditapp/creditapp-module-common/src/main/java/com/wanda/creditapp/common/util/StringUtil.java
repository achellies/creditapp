package com.wanda.creditapp.common.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {
	public static String getFirstUpper(String str) {
		String newStr = "";
		if (str.length() > 0) {
			newStr = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
		}
		return newStr;
	}
}
