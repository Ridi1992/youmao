package com.lester.youmao.entity;

public class Coupon {

	private String type_id; 
	private String id; 
	private String coupons_id;
	private String buy_number;
	private double coupons_discount;
	private String type_money; 
	private String use_amount; 
	private String start_time; 
	private String end_time; 
	private String add_time; 
	private String coupons_type; 
	private String coupons_status;
	private boolean isCheck;
	
	public String getCoupons_id() {
		return coupons_id;
	}
	public void setCoupons_id(String coupons_id) {
		this.coupons_id = coupons_id;
	}
	public boolean isCheck() {
		return isCheck;
	}
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBuy_number() {
		return buy_number;
	}
	public void setBuy_number(String buy_number) {
		this.buy_number = buy_number;
	}
	public double getCoupons_discount() {
		return coupons_discount;
	}
	public void setCoupons_discount(double coupons_discount) {
		this.coupons_discount = coupons_discount;
	}
	public String getType_money() {
		return type_money;
	}
	public void setType_money(String type_money) {
		this.type_money = type_money;
	}
	public String getUse_amount() {
		return use_amount;
	}
	public void setUse_amount(String use_amount) {
		this.use_amount = use_amount;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getCoupons_type() {
		return coupons_type;
	}
	public void setCoupons_type(String coupons_type) {
		this.coupons_type = coupons_type;
	}
	public String getCoupons_status() {
		return coupons_status;
	}
	public void setCoupons_status(String coupons_status) {
		this.coupons_status = coupons_status;
	} 
	
}
