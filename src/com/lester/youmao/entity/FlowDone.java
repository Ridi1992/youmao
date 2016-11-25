package com.lester.youmao.entity;

public class FlowDone {
	
	private String order_sn;
	private String order_id;
	private OrderInfo order_info;
	
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public OrderInfo getOrder_info() {
		return order_info;
	}
	public void setOrder_info(OrderInfo order_info) {
		this.order_info = order_info;
	}

}
