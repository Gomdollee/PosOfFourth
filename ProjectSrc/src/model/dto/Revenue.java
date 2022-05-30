package model.dto;

public class Revenue {
	private String date;
	private int total;

	public Revenue(String date, int total) {
		super();
		this.date = date;
		this.total = total;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(date);
		builder.append("의 매출 : ");
		builder.append(total);
		builder.append("원 입니다");
		return builder.toString();
	}

}
