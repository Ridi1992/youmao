package com.lester.youmao.entity;

public class HomePromote {
	
	private String goods_id;
	private String goods_name;
	private String shop_price;
	private String goods_thumb;
	private String promote_price;
	private long promote_end_date;
	
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
	public String getShop_price() {
		return shop_price;
	}
	public void setShop_price(String shop_price) {
		this.shop_price = shop_price;
	}
	public String getGoods_thumb() {
		return goods_thumb;
	}
	public void setGoods_thumb(String goods_thumb) {
		this.goods_thumb = goods_thumb;
	}
	public String getPromote_price() {
		return promote_price;
	}
	public void setPromote_price(String promote_price) {
		this.promote_price = promote_price;
	}
	public long getPromote_end_date() {
		return promote_end_date;
	}
	public void setPromote_end_date(long promote_end_date) {
		this.promote_end_date = promote_end_date;
	}

}
