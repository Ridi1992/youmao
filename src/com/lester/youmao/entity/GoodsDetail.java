package com.lester.youmao.entity;

import java.util.ArrayList;

public class GoodsDetail {
	
	private String id;
	private String cat_id;
	private String goods_name;
	private String goods_brief;
	private double shop_price;
	private double market_price;
	private String salesnum;
	private String goods_number;
	private double promote_price;
	private String formated_promote_price;
	private ArrayList<Pictures> pictures;
	private ArrayList<Specification> specification;
	private String exchange_integral;
	private String goods_comment;
	
	public String getGoods_comment() {
		return goods_comment;
	}
	public void setGoods_comment(String goods_comment) {
		this.goods_comment = goods_comment;
	}
	public String getExchange_integral() {
		return exchange_integral;
	}
	public void setExchange_integral(String exchange_integral) {
		this.exchange_integral = exchange_integral;
	}
	private Img img;
	
	public Img getImg() {
		return img;
	}
	public void setImg(Img img) {
		this.img = img;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_brief() {
		return goods_brief;
	}
	public void setGoods_brief(String goods_brief) {
		this.goods_brief = goods_brief;
	}
	public double getShop_price() {
		return shop_price;
	}
	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}
	public double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}
	public String getSalesnum() {
		return salesnum;
	}
	public void setSalesnum(String salesnum) {
		this.salesnum = salesnum;
	}
	public String getGoods_number() {
		return goods_number;
	}
	public void setGoods_number(String goods_number) {
		this.goods_number = goods_number;
	}
	public double getPromote_price() {
		return promote_price;
	}
	public void setPromote_price(double promote_price) {
		this.promote_price = promote_price;
	}
	public String getFormated_promote_price() {
		return formated_promote_price;
	}
	public void setFormated_promote_price(String formated_promote_price) {
		this.formated_promote_price = formated_promote_price;
	}
	public ArrayList<Pictures> getPictures() {
		return pictures;
	}
	public void setPictures(ArrayList<Pictures> pictures) {
		this.pictures = pictures;
	}
	public ArrayList<Specification> getSpecification() {
		return specification;
	}
	public void setSpecification(ArrayList<Specification> specification) {
		this.specification = specification;
	}

}
