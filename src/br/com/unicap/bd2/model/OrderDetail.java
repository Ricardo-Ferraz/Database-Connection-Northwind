package br.com.unicap.bd2.model;

public class OrderDetail {

	private String orderId;
	private String productId;
	private String unitPrice;
	private String quantity;
	private String discount;

	public OrderDetail(String orderId, String productId) {
		this.orderId = orderId;
		this.productId = productId;
	}

	public OrderDetail(String orderId, String productId, String unitPrice, String quantity, String discount) {
		this.orderId = orderId;
		this.productId = productId;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.discount = discount;
	}

	public String getOrderId() {
		return this.orderId;
	}
	
	public String getProductId() {
		return this.productId;
	}

	public String getIndex(int i) {
		switch (i) {
			case 1:
				return this.orderId;
				
			case 2:
				return this.productId;

			case 3:
				return this.unitPrice;

			case 4:
				return this.quantity;
				
			case 5:
				return this.discount;

		}
		
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof OrderDetail)) {
			return false;
		}
		OrderDetail c = (OrderDetail) o;

		return c.getOrderId().equalsIgnoreCase(this.orderId) && c.getProductId().equalsIgnoreCase(this.productId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("orderId: ").append(this.orderId).append("\n");
		sb.append("productId: ").append(this.productId).append("\n");
		sb.append("unitPrice: ").append(this.unitPrice).append("\n");
		sb.append("quantity: ").append(this.quantity).append("\n");
		sb.append("discount: ").append(this.discount).append("\n");

		return sb.toString();
	}

}
