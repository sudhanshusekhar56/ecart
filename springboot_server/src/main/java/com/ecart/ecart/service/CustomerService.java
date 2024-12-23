package com.ecart.ecart.service;

import java.sql.SQLException;
import java.util.List;

import com.ecart.ecart.bean.Customer;
import com.ecart.ecart.bean.Order;
import com.ecart.ecart.bean.OrderResponse;
import com.ecart.ecart.bean.Product;
import com.ecart.ecart.dao.CustomerDao;
import com.ecart.ecart.dao.OrderDao;

public class CustomerService {
	public boolean addCustomer(Customer customer) throws SQLException {
		CustomerDao c = new CustomerDao();
		return c.addCustomer(customer);
	}

	public Customer validateCustomer(String email, String password) {
		CustomerDao c = new CustomerDao();
		return c.validateCustomer(email, password);
	}
	
	public List<OrderResponse> getCartItemsForCustomer(int customerId) {
		OrderDao o = new OrderDao();
		return o.getCartItemsForCustomer(customerId);
	}
	
	public boolean updateCustomerProfile(Customer customer) {
		CustomerDao c = new CustomerDao();
		return c.updateCustomerProfile(customer);	
	}
	
	public boolean addItemToCart(int orderId, int customerId, int productId, int OrderQty) {
		OrderDao o = new OrderDao();
		return o.addItemToCart(orderId, customerId, productId, OrderQty);
	}
	
	public boolean updateItemInCart(int orderId, int OrderQty) {
		OrderDao o = new OrderDao();
		return o.updateItemInCart(orderId, OrderQty);
	}

	public boolean deleteCartItemById(int orderId, int customerId) {
		OrderDao o = new OrderDao();
		return o.deleteCartItemById(orderId, customerId);
	}

	public boolean buyItem(Order order) {
		OrderDao o = new OrderDao();
		return o.buyItem(order);
	}
	
	public boolean buyNewItem(Order order) {
		OrderDao o = new OrderDao();
		return o.buyNewItem(order);
	}

	public List<OrderResponse> getOrderItemsForCustomer(int customerId){
		OrderDao o = new OrderDao();
		return o.getOrderItemsForCustomer(customerId);
	}

	public boolean deleteOrderItem(int orderId, int customerId) {
		OrderDao o = new OrderDao();
		return o.deleteCartItemById(orderId, customerId);
	}

	public boolean changePassword(int customerId, String newPassword) {
		CustomerDao c = new CustomerDao();
		return c.changePassword(customerId, newPassword);
	}

	public String getOldPassword(int customerId) {
		CustomerDao c = new CustomerDao();
		return c.getOldPassword(customerId);
	}
}
