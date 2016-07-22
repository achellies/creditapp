package com.wanda.creditapp.test;

import java.io.Serializable;

public class BaseUser implements Serializable {
	private static final long serialVersionUID = 1L;
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
