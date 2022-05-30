package model.dto;

public class Goods {
	
	private String goodsId;
	private String goodsName;
	private int stockQty;
	private int goodsPrice;
	
	public Goods(String goodsId, String goodsName, int stockQty, int goodsPrice) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.stockQty = stockQty;
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public int getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" ");
		builder.append(goodsId);
		builder.append("\t | \t");
		builder.append(goodsName);
		builder.append("\t | \t");
		builder.append(stockQty);
		builder.append("\t | \t");
		builder.append(goodsPrice);
		builder.append(" ");
		return builder.toString();
	}
	
}