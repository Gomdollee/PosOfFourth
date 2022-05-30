package model.dto;

public class Dealer {
	private String dealerId;
	private String dealerType;
	private String dealerName;
	private String dealerAddr;
	private String dealerPhone;
	
	public Dealer(String dealerId, String dealerType, String dealerName, String dealerAddr, String dealerPhone) {
		this.dealerId = dealerId;
		this.dealerType = dealerType;
		this.dealerName = dealerName;
		this.dealerAddr = dealerAddr;
		this.dealerPhone = dealerPhone;
	}
	
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	public String getDealerType() {
		return dealerType;
	}
	public void setDealerType(String dealerType) {
		this.dealerType = dealerType;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getDealerAddr() {
		return dealerAddr;
	}
	public void setDealerAddr(String dealerAddr) {
		this.dealerAddr = dealerAddr;
	}
	public String getDealerPhone() {
		return dealerPhone;
	}
	public void setDealerPhone(String dealerPhone) {
		this.dealerPhone = dealerPhone;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" ");
		builder.append(dealerId);
		builder.append("\t | \t");
		builder.append(dealerType);
		builder.append("\t | \t");
		builder.append(dealerName);
		builder.append("\t | \t");
		builder.append(dealerAddr);
		builder.append("\t | \t");
		builder.append(dealerPhone);
		builder.append(" ");
		return builder.toString();
	}
	
	
}
