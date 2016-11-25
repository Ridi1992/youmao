package com.lester.youmao.userinfo;

import java.io.Serializable;

public class User implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6810069635574151912L;
	private String user_id;
	private String user_name;
	private String sex;
	private String face_img;
	private String key1;
	
	
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public String getFace_img() {
		return face_img;
	}
	public void setFace_img(String face_img) {
		this.face_img = face_img;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

}
