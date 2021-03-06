package com.lester.youmao.entity;

import java.util.ArrayList;

public class OrderList {

	private String rec_id;
	private String goods_id;
	private String goods_name;
	private String goods_number;
	private ArrayList<Goods_attr> goods_attr;
	private String formated_subtotal;
	private String goods_thumb;
	private double subtotal;
	private String exchange_integral;
	
	public String getExchange_integral() {
		return exchange_integral;
	}
	public void setExchange_integral(String exchange_integral) {
		this.exchange_integral = exchange_integral;
	}
	public String getGoods_thumb() {
		return goods_thumb;
	}
	public void setGoods_thumb(String goods_thumb) {
		this.goods_thumb = goods_thumb;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public String getRec_id() {
		return rec_id;
	}
	public void setRec_id(String rec_id) {
		this.rec_id = rec_id;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_number() {
		return goods_number;
	}
	public void setGoods_number(String goods_number) {
		this.goods_number = goods_number;
	}
	public ArrayList<Goods_attr> getGoods_attr() {
		return goods_attr;
	}
	public void setGoods_attr(ArrayList<Goods_attr> goods_attr) {
		this.goods_attr = goods_attr;
	}
	public String getFormated_subtotal() {
		return formated_subtotal;
	}
	public void setFormated_subtotal(String formated_subtotal) {
		this.formated_subtotal = formated_subtotal;
	}
	
	
	
}
