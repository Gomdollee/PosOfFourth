package model.dto;

public class Customer {
	private String cusId;
	private String cusPwd;
	
	public Customer() {}

	public Customer(String cusId, String cusPwd) {
		super();
		this.cusId = cusId;
		this.cusPwd = cusPwd;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusPwd() {
		return cusPwd;
	}

	public void setCusPwd(String cusPwd) {
		this.cusPwd = cusPwd;
	}

}
