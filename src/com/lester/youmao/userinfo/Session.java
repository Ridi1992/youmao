package com.lester.youmao.userinfo;

import java.io.Serializable;

public class Session implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -318807386424511192L;
	private String sid;
	private String uid;
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

}
