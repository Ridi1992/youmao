package com.lester.youmao.entity;

import java.io.Serializable;

public class OrderInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7936683827017713511L;
	private String pay_code;
	private String order_amount;
	private String order_id;
	private String subject;
	private String order_sn;
	
	public String getPay_code() {
		return pay_code;
	}
	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}
	public String getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(String order_amount) {
		this.order_amount = order_amount;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	
}
