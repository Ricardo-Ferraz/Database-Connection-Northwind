package br.com.unicap.bd2.model;

public class Customer {

	private String customerId;
	private String companyName;
	private String contactName;
	private String contactTitle;
	private String address;
	private String city;
	private String region;
	private String postalCode;
	private String country;
	private String phone;
	private String fax;

	public Customer(String customerId, String companyName) {
		this.customerId = customerId;
		this.companyName = companyName;
	}

	public Customer(String customerId, String companyName, String contactName, String contactTitle, String address,
			String city, String region, String postalCode, String country, String phone, String fax) {
		this.customerId= customerId;
		this.companyName= companyName;
		this.contactName= contactName;
		this.contactTitle= contactTitle;
		this.address= address;
		this.city= city;
		this.region= region;
		this.postalCode= postalCode;
		this.country= country;
		this.phone= phone;
		this.fax= fax;
	}
	
	public String getCustomerId() {
		return this.customerId;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Customer)) {
			return false;
		}
		Customer c = (Customer) o;
		
		return c.getCustomerId() == this.customerId;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CustomerID: ").append(this.customerId).append("\n");
		sb.append("CompanyName: ").append(this.companyName).append("\n");
		sb.append("ContactName: ").append(this.contactName).append("\n");
		sb.append("ContactTitle: ").append(this.contactTitle).append("\n");
		sb.append("Address: ").append(this.address).append("\n");
		sb.append("City: ").append(this.city).append("\n");
		sb.append("Region: ").append(this.region).append("\n");
		sb.append("PostalCode: ").append(this.postalCode).append("\n");
		sb.append("Country: ").append(this.country).append("\n");
		sb.append("Phone: ").append(this.phone).append("\n");
		sb.append("Fax: ").append(this.fax).append("\n");
		
		return sb.toString();
	}

}
