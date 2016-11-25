package com.lester.youmao.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class CateGory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5139294323521758344L;
	
	private String cat_id;
	private String cat_name;
	private String cat_img;
	private boolean isCheck;
	
	public boolean isCheck() {
		return isCheck;
	}
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	private ArrayList<CateGory> son;
	
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public String getCat_img() {
		return cat_img;
	}
	public void setCat_img(String cat_img) {
		this.cat_img = cat_img;
	}
	public ArrayList<CateGory> getSon() {
		return son;
	}
	public void setSon(ArrayList<CateGory> son) {
		this.son = son;
	}
	
	

}
