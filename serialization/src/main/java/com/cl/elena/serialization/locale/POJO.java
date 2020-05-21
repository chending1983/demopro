package com.cl.elena.serialization.locale;

import java.io.Serializable;

public class POJO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int age;

	private String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
