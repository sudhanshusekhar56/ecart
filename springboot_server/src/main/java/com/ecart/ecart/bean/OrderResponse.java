package com.ecart.ecart.bean;

public class OrderResponse {
	   private int productId;
	   private String productName;
	   private double productPrice;
	   private String productCategory;
	   private String productDescription;
	   private int orderId;
	   private int orderQty; 
	   private String orderStatus;
	   private String orderDate;
	   private String arrivalDate;
	   private String orderAddress;
	public int getProductId() {
		return productId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getorderId() {
		return orderId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public int getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	@Override
	public String toString() {
		return "OrderResponse [productId=" + productId + ", productName=" + productName + ", productPrice="
				+ productPrice + ", productCategory=" + productCategory + ", productDescription=" + productDescription
				+ ", orderQty=" + orderQty + ", orderStatus=" + orderStatus + ", orderDate=" + orderDate
				+ ", arrivalDate=" + arrivalDate + ", orderAddress=" + orderAddress + "]";
	}
	public OrderResponse(int productId, String productName, double productPrice, String productCategory,
			String productDescription,int orderId, int orderQty, String orderStatus, String orderDate, String arrivalDate,
			String orderAddress) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.productDescription = productDescription;
		this.orderId = orderId;
		this.orderQty = orderQty;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.arrivalDate = arrivalDate;
		this.orderAddress = orderAddress;
	}
	public OrderResponse() {
		super();
	}
	   
	   
}
