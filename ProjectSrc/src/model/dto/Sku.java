package model.dto;

public class Sku {

	private String skuId;
	private String dealerId;
	private String skuName;
	private int skuPrice;

	public Sku(String skuId, String dealerId, String skuName, int skuPrice) {
		super();
		this.skuId = skuId;
		this.dealerId = dealerId;
		this.skuName = skuName;
		this.skuPrice = skuPrice;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public int getSkuPrice() {
		return skuPrice;
	}

	public void setSkuPrice(int skuPrice) {
		this.skuPrice = skuPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" ");
		builder.append(skuId);
		builder.append("\t | \t");
		builder.append(dealerId);
		builder.append("\t | \t");
		builder.append(skuName);
		builder.append("\t | \t");
		builder.append(skuPrice);
		builder.append("원");
		return builder.toString();
	}

}