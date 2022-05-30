package model.dto;

import java.util.ArrayList;
import java.util.List;

public class Ib {
	private String ibId;
	private String dealerId;
	private String ibDate;
	
	private List<IbLine> ibLineList = new ArrayList<IbLine>();
	
	public Ib(String ibId, String dealerId, String ibDate) {
		this.ibId=ibId;
		this.dealerId=dealerId;
		this.ibDate=ibDate;
	}
	
	public String getIbId() {
		return ibId;
	}
	public void setIbId(String ibId) {
		this.ibId = ibId;
	}
	public String getDealerId() {
		return dealerId;
	}
	public void setDearId(String dearId) {
		this.dealerId = dearId;
	}
	public String getIbDate() {
		return ibDate;
	}
	public void setIbDate(String ibDate) {
		this.ibDate = ibDate;
	}
	public List<IbLine> getIbLineList() {
		return ibLineList;
	}
	public void setIbLineList(List<IbLine> ibLineList) {
		this.ibLineList = ibLineList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
//		builder.append("Ib [ibId=");
//		builder.append(ibId);
//		builder.append(", dealerId=");
//		builder.append(dealerId);
//		builder.append(", ibDate=");
//		builder.append(ibDate);
//		builder.append(" ");
		builder.append(ibLineList);
//		builder.append(" ");
		return builder.toString();
	}
	
	
}