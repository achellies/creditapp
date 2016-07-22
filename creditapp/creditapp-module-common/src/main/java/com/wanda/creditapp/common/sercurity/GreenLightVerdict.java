package com.wanda.creditapp.common.sercurity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析表达式，有待完善 配置不需要做权限校验的URL
 * 
 * @author Administrator
 * 
 */
public class GreenLightVerdict {
	private List<String> expressions = new ArrayList<String>();

	private static List<Pattern> matcherList = new ArrayList<Pattern>();

	public void initMatchers() {
		for (String expression : expressions) {
			String regEx = expression.replaceAll("\\*", "[0-9A-Za-z/_.]{0,}").replaceAll("\\.", "[.]{1}");
			Pattern pattern = Pattern.compile(regEx);
			matcherList.add(pattern);
		}
	}

	public boolean greenLight(String path) {
		boolean light = false;
		Matcher matcher;
		for (Pattern pattern : matcherList) {
			if (light)
				return light;
			matcher = pattern.matcher(path);
			light = matcher.matches();
		}
		return light;
	}

	public static void main(String[] args) {
		GreenLightVerdict g = new GreenLightVerdict();
		g.expressions.add("/assets/*");
		g.initMatchers();
		System.out.println(g.greenLight("/assetss/_a.html"));
	}

	public List<String> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<String> expressions) {
		this.expressions = expressions;
	}

}
