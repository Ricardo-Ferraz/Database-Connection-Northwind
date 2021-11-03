package br.com.unicap.bd2.model;

public class Order {

	private String orderId;
	private String customerId;
	private String employeeId;
	private String orderDate;
	private String requiredDate;
	private String shippedDate;
	private String shipVia;
	private String freight;
	private String shipName;
	private String shipAddress;
	private String shipCity;
	private String shipRegion;
	private String shipPostalCode;
	private String shipCountry;

	public Order(String orderId, String customerId) {
		this.orderId = orderId;
		this.customerId = customerId;
	}

	public Order(String orderId, String customerId, String employeeId, String orderDate, String requiredDate,
			String shippedDate, String shipVia, String freight, String shipName, String shipAddress, String shipCity,
			String shipRegion, String shipPostalCode, String shipCountry) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.employeeId = employeeId;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.shipVia = shipVia;
		this.freight = freight;
		this.shipName = shipName;
		this.shipAddress = shipAddress;
		this.shipCity = shipCity;
		this.shipRegion = shipRegion;
		this.shipPostalCode = shipPostalCode;
		this.shipCountry = shipCountry;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public String getIndex(int i) {
		switch (i) {
			case 1:
				return this.orderId;
				
			case 2:
				return this.customerId;

			case 3:
				return this.employeeId;

			case 4:
				return this.orderDate;
				
			case 5:
				return this.requiredDate;
				
			case 6:
				return this.shippedDate;
				
			case 7:
				return this.shipVia;
				
			case 8:
				return this.freight;
				
			case 9:
				return this.shipName;
				
			case 10:
				return this.shipAddress;
				
			case 11:
				return this.shipCity;
				
			case 12:
				return this.shipRegion;
				
			case 13:
				return this.shipPostalCode;
				
			case 14:
				return this.shipCountry;

		}
		
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Order)) {
			return false;
		}
		Order c = (Order) o;

		return c.getOrderId().equalsIgnoreCase(this.orderId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("orderId: ").append(this.orderId).append("\n");
		sb.append("customerId: ").append(this.customerId).append("\n");
		sb.append("employeeId: ").append(this.employeeId).append("\n");
		sb.append("orderDate: ").append(this.orderDate).append("\n");
		sb.append("requiredDate: ").append(this.requiredDate).append("\n");
		sb.append("shippedDate: ").append(this.shippedDate).append("\n");
		sb.append("shipVia: ").append(this.shipVia).append("\n");
		sb.append("freight: ").append(this.freight).append("\n");
		sb.append("shipName: ").append(this.shipName).append("\n");
		sb.append("shipAddress: ").append(this.shipAddress).append("\n");
		sb.append("shipCity: ").append(this.shipCity).append("\n");
		sb.append("shipRegion: ").append(this.shipRegion).append("\n");
		sb.append("shipPostalCode: ").append(this.shipPostalCode).append("\n");
		sb.append("shipCountry: ").append(this.shipCountry).append("\n");

		return sb.toString();
	}

}
