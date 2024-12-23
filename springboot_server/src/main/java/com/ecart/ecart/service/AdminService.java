package com.ecart.ecart.service;

import java.util.ArrayList;
import java.util.List;

import com.ecart.ecart.bean.Customer;
import com.ecart.ecart.bean.OrderResponse;
import com.ecart.ecart.bean.Product;
import com.ecart.ecart.dao.CustomerDao;
import com.ecart.ecart.dao.OrderDao;
import com.ecart.ecart.dao.ProductDao;

public class AdminService {
	public ArrayList<Customer> getCustomers(){
			CustomerDao c = new CustomerDao();
			return c.getCustomers();			
	}

	public boolean deleteCustomerById(int id) {
		CustomerDao c = new CustomerDao();
		return c.deleteCustomerById(id);
	}
	
	public ArrayList<Product> getProducts(){
		ProductDao p = new ProductDao();
		return p.getProducts();			
}

	public boolean deleteProductById(int id) {
		ProductDao p = new ProductDao();
		return p.deleteProductById(id);

	}
	
	public boolean addProduct(Product product) {
		ProductDao p = new ProductDao();
		return p.addProduct(product);
	}

	
	public List<OrderResponse> getAllOrdres() {
		OrderDao o = new OrderDao();
		return o.getAllOrders();
	}

	public boolean updateOrderStatus(int productId, String orderStatus) {
		OrderDao o = new OrderDao();
		return o.updateOrderStatus(productId, orderStatus);
	}

	public boolean updateProductData(Product product) {
		ProductDao p = new ProductDao();
		return p.updateProductData(product);
	}

}
