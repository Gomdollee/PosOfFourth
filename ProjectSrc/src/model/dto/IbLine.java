package model.dto;

public class IbLine {
	private String ibLineId;
	private String ibId;
	private String goodsId;
	private int ibQty;
	private int ibPrice;

	public IbLine(String ibLineId, String ibId, String goodsId, int ibQty, int ibPrice) {
		super();
		this.ibLineId = ibLineId;
		this.ibId = ibId;
		this.goodsId = goodsId;
		this.ibQty = ibQty;
		this.ibPrice = ibPrice;
	}

	public String getIbLineId() {
		return ibLineId;
	}

	public void setIbLineId(String ibLineId) {
		this.ibLineId = ibLineId;
	}

	public String getIbId() {
		return ibId;
	}

	public void setIbId(String ibId) {
		this.ibId = ibId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public int getIbQty() {
		return ibQty;
	}

	public void setIbQty(int ibQty) {
		this.ibQty = ibQty;
	}

	public int getIbPrice() {
		return ibPrice;
	}

	public void setIbPrice(int ibPrice) {
		this.ibPrice = ibPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IbLine [ibLineId=");
		builder.append(ibLineId);
		builder.append(", ibId=");
		builder.append(ibId);
		builder.append(", goodsId=");
		builder.append(goodsId);
		builder.append(", ibQty=");
		builder.append(ibQty);
		builder.append(", ibPrice=");
		builder.append(ibPrice);
		builder.append("]");
		return builder.toString();
	}

}
