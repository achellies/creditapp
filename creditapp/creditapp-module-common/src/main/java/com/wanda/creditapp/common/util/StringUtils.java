package com.wanda.creditapp.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class StringUtils {

	/**
	 * 检查指定的字符串是否为空。
	 * <ul>
	 * <li>SysUtils.isEmpty(null) = true</li>
	 * <li>SysUtils.isEmpty("") = true</li>
	 * <li>SysUtils.isEmpty("   ") = true</li>
	 * <li>SysUtils.isEmpty("abc") = false</li>
	 * </ul>
	 * 
	 * @param value
	 *            待检查的字符串
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isNullOrEmpty(String value) {
		return value == null || isEmpty(value);
	}

	public static boolean isBlank(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String value) {
		return !isBlank(value);
	}
	
	public static String trim(String value) {
		return value != null ? value.trim() : value;
	}

	/**
	 * 检查对象是否为数字型字符串,包含负数开头的。
	 */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if (length < 1)
			return false;

		int i = 0;
		if (length > 1 && chars[0] == '-')
			i = 1;

		for (; i < length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查指定的字符串列表是否不为空。
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	/**
	 * 把通用字符编码的字符串转化为汉字编码。
	 */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}

	/**
	 * 过滤不可见字符
	 */
	public static String stripNonValidXMLCharacters(String input) {
		if (input == null || ("".equals(input)))
			return "";
		StringBuilder out = new StringBuilder();
		char current;
		for (int i = 0; i < input.length(); i++) {
			current = input.charAt(i);
			if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF)))
				out.append(current);
		}
		return out.toString();
	}

	/**
	 * Description：首字母转大写
	 */
	public static String getFirstUpper(String str) {
		String newStr = "";
		if (str.length() > 0) {
			newStr = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
		}
		return newStr;
	}
	/**
	 * Description：首字母转小写
	 */
	public static String getFirstLower(String str) {
		String newStr = "";
		if (str.length() > 0) {
			newStr = str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
		}
		return newStr;
	}

	/**
	 * 当前值为空则取默认值返回
	 * 
	 * @param value
	 * @param defaultVal
	 * @return
	 */
	public static String defaultIfBlank(String value, String defaultVal) {
		return StringUtils.isNotBlank(value) ? value : defaultVal;
	}

	/**
	 * 替换指定位置的字符
	 * 
	 * @param index
	 *            位置
	 * @param ostr
	 *            源字符串
	 * @param replaceChar
	 *            替换的字符
	 * @return
	 */
	public static String replaceAtIndex(int index, String ostr, String replaceChar) {
		if (isNotBlank(ostr)) {
			try {
				return ostr.replaceFirst(ostr.charAt(index) + "", replaceChar);
			} catch (Exception e) {
				return ostr;
			}
		} else {
			return ostr;
		}
	}

	/**
	 * 填充左边字符
	 * 
	 * @param source
	 *            源字符串
	 * @param fillChar
	 *            填充字符
	 * @param len
	 *            填充到的长度
	 * @return 填充后的字符串
	 */
	public static String fillLeft(String source, char fillChar, int len) {
		StringBuffer ret = new StringBuffer();
		if (null == source)
			ret.append("");
		if (source.length() > len) {
			ret.append(source);
		} else {
			int slen = source.length();
			while (ret.toString().length() + slen < len) {
				ret.append(fillChar);
			}
			ret.append(source);
		}
		return ret.toString();
	}

	/**
	 * 填充右边字符
	 * 
	 * @param source
	 *            源字符串
	 * @param fillChar
	 *            填充字符
	 * @param len
	 *            填充到的长度
	 * @return 填充后的字符串
	 */
	public static String filRight(String source, char fillChar, int len) {
		StringBuffer ret = new StringBuffer();
		if (null == source)
			ret.append("");
		if (source.length() > len) {
			ret.append(source);
		} else {
			ret.append(source);
			while (ret.toString().length() < len) {
				ret.append(fillChar);
			}
		}
		return ret.toString();
	}

	public static String filterStr(String str) {
		if (null == str || "".equals(str)) {
			return str;
		}
		str = str.replaceAll("'", "''");
		return str;
	}

	/**
	 * 字符串中没有number，全是字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isAllChar(String str) {
		String regex = ".*[a-zA-Z]+.*";
		Matcher m = Pattern.compile(regex).matcher(str);
		return m.matches();
	}

	/**
	 * 判断字符串是否相等
	 * @param cs1
	 * @param cs2
	 * @return
	 */
	public static boolean equals(CharSequence cs1, CharSequence cs2) {
		return cs1 == null ? cs2 == null : cs1.equals(cs2);
	}
	
	public static boolean isMapJson(String str) {
		if (StringUtils.isBlank(str)) return false;
		if (str.indexOf("{") == 0 && str.lastIndexOf("}") == str.length()-1) {
			return true;
		}
		return false;
	}
}
