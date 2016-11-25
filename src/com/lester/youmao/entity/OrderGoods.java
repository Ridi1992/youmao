package com.lester.youmao.entity;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcel;

public class OrderGoods implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2781415521334390186L;
	
	private String goods_id;
	private String order_id;
	private String name;
	private String goods_number;
	private String subtotal;
	private String formated_shop_price;
	private ArrayList<String> goods_spec;
	private String order_integral;
	private Img img;
	private String is_comment;
	private boolean isCheck;
	
	public boolean isCheck() {
		return isCheck;
	}
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getOrder_integral() {
		return order_integral;
	}
	public void setOrder_integral(String order_integral) {
		this.order_integral = order_integral;
	}
	public String getIs_comment() {
		return is_comment;
	}
	public void setIs_comment(String is_comment) {
		this.is_comment = is_comment;
	}
	public ArrayList<String> getGoods_spec() {
		return goods_spec;
	}
	public void setGoods_spec(ArrayList<String> goods_spec) {
		this.goods_spec = goods_spec;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGoods_number() {
		return goods_number;
	}
	public void setGoods_number(String goods_number) {
		this.goods_number = goods_number;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getFormated_shop_price() {
		return formated_shop_price;
	}
	public void setFormated_shop_price(String formated_shop_price) {
		this.formated_shop_price = formated_shop_price;
	}
	public Img getImg() {
		return img;
	}
	public void setImg(Img img) {
		this.img = img;
	}
}
