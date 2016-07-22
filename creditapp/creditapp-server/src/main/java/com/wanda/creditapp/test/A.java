package com.wanda.creditapp.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class A {
	public static void main(String[] args) {
		ObjectMapper om=new ObjectMapper();	
		User u=new User();
		u.setId("122");
		u.setUserName("张三");
		try {
			System.out.println(om.writeValueAsString(u));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
