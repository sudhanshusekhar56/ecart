package com.ecart.ecart.bean;

public class Order {
	  private int orderId;
	  private int customerId;
	  private int productId;
	  private String orderDate;
	  private String arrivalDate;
	  private String orderStatus;
	  private boolean inCart;
	  private String orderAddress;
	  private int orderQty;

	  public Order() {

	  }


	  public Order(int orderId, int customerId, int productId, String orderDate, 

	         String arrivalDate, String orderStatus, boolean inCart, 

	         String orderAddress, int orderQty) {

	    this.orderId = orderId;

	    this.customerId = customerId;

	    this.productId = productId;

	    this.orderDate = orderDate;

	    this.arrivalDate = arrivalDate;

	    this.orderStatus = orderStatus;

	    this.inCart = inCart;

	    this.orderAddress = orderAddress;

	    this.orderQty = orderQty;

	  }

	  public int getOrderId() {

	    return orderId;

	  }

	  public void setOrderId(int orderId) {

	    this.orderId = orderId;

	  }

	  public int getCustomerId() {

	    return customerId;

	  }

	  public void setCustomerId(int customerId) {

	    this.customerId = customerId;

	  }

	  public int getProductId() {

	    return productId;

	  }

	  public void setProductId(int productId) {

	    this.productId = productId;

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

	  public String getOrderStatus() {

	    return orderStatus;

	  }

	  public void setOrderStatus(String orderStatus) {

	    this.orderStatus = orderStatus;

	  }

	  public boolean isInCart() {

	    return inCart;

	  }

	  public void setInCart(boolean inCart) {

	    this.inCart = inCart;

	  }

	  public String getOrderAddress() {

	    return orderAddress;

	  }

	  public void setOrderAddress(String orderAddress) {

	    this.orderAddress = orderAddress;

	  }

	  public int getOrderQty() {

	    return orderQty;

	  }

	  public void setOrderQty(int orderQty) {

	    this.orderQty = orderQty;

	  }

	  @Override

	  public String toString() {

	    return "Orders{" +

	        "orderId=" + orderId +

	        ", customerId=" + customerId +

	        ", productId=" + productId +

	        ", orderDate='" + orderDate + '\'' +

	        ", arrivalDate='" + arrivalDate + '\'' +

	        ", orderStatus='" + orderStatus + '\'' +

	        ", inCart=" + inCart +

	        ", orderAddress='" + orderAddress + '\'' +

	        ", orderQty=" + orderQty +

	        '}';

	  }

	}

