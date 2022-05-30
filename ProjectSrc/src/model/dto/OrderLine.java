package model.dto;

public class OrderLine {
	private String orderLineId;
	private String orderId;
	private String goodsId;
	private int goodsPrice;
	private int orderQty;

	public OrderLine() {
	}

	public OrderLine(String orderLineId, String orderId, String goodsId, int goodsPrice, int orderQty) {
		super();
		this.orderLineId = orderLineId;
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.goodsPrice = goodsPrice;
		this.orderQty = orderQty;
	}

	public String getOrderLineId() {
		return orderLineId;
	}

	public void setOrderLineId(String orderLineId) {
		this.orderLineId = orderLineId;
	}

	public String getOderId() {
		return orderId;
	}

	public void setOderId(String orderId) {
		this.orderId = orderId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public int getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderLine [OrderLineId=");
		builder.append(orderLineId);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", goodsId=");
		builder.append(goodsId);
		builder.append(", goodsPrice=");
		builder.append(goodsPrice);
		builder.append(", orderQty=");
		builder.append(orderQty);
		builder.append("]");
		return builder.toString();
	}
}
		
