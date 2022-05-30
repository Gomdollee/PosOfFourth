package model.dto;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

public class Orders {
	private String orderId; //pk
	private String cusId;
	private String date;

	private List<OrderLine> orderLineList = new ArrayList<OrderLine>();

	public Orders() {
	}

	public Orders(String orderId, String cusId, String date) {
		super();
		this.orderId = orderId;
		this.cusId = cusId;
		this.date = date;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public List<OrderLine> getOrderLineList() {
		return orderLineList;
	}

	public void setOrderLineList(List<OrderLine> orderLineList) {
		this.orderLineList = orderLineList;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
