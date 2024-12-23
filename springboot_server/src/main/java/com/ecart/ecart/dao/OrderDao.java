package com.ecart.ecart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecart.ecart.bean.Order;
import com.ecart.ecart.bean.OrderResponse;
import com.ecart.ecart.bean.Product;
import com.ecart.ecart.util.Util;

public class OrderDao {
	public List<OrderResponse> getCartItemsForCustomer(int customerId) {
	   ArrayList<OrderResponse> productsInCart = new ArrayList<>();
	   String sql = "SELECT p.productId, p.productName, p.productPrice, p.productCategory, p.productDescription, " +
            "o.orderId, o.orderQty, o.orderStatus, o.orderDate, o.arrivalDate, o.orderAddress " +
            "FROM orders o " +
            "JOIN product p ON o.productId = p.productId " +
            "WHERE o.customerId = ? and inCart = true";
		try  {
			Connection cn = Util.getConnection();
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, customerId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrderResponse orderResponse = new OrderResponse(
			       rs.getInt("productId"),              
			       rs.getString("productName"),         
			       rs.getDouble("productPrice"),        
			       rs.getString("productCategory"),     
			       rs.getString("productDescription"),  
			       rs.getInt("orderId"),
			       rs.getInt("orderQty"),               
			       rs.getString("orderStatus"),         
			       rs.getString("orderDate"),           
			       rs.getString("arrivalDate"),         
			       rs.getString("orderAddress"));       
				productsInCart.add(orderResponse);

			}
			} catch (SQLException e) {
		        e.printStackTrace();
		}

	    return productsInCart;

   }
	
	public List<OrderResponse> getOrderItemsForCustomer(int customerId) {
		   ArrayList<OrderResponse> productsInCart = new ArrayList<>();
		   String sql = "SELECT p.productId, p.productName, p.productPrice, p.productCategory, p.productDescription, " +
	            "o.orderId, o.orderQty, o.orderStatus, o.orderDate, o.arrivalDate, o.orderAddress " +
	            "FROM orders o " +
	            "JOIN product p ON o.productId = p.productId " +
	            "WHERE o.customerId = ? and inCart = false";
			try  {
				Connection cn = Util.getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setInt(1, customerId);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					OrderResponse orderResponse = new OrderResponse(
				       rs.getInt("productId"),              
				       rs.getString("productName"),         
				       rs.getDouble("productPrice"),        
				       rs.getString("productCategory"),     
				       rs.getString("productDescription"),  
				       rs.getInt("orderId"),
				       rs.getInt("orderQty"),               
				       rs.getString("orderStatus"),         
				       rs.getString("orderDate"),           
				       rs.getString("arrivalDate"),         
				       rs.getString("orderAddress"));       
					productsInCart.add(orderResponse);

				}
				} catch (SQLException e) {
			        e.printStackTrace();
			}

		    return productsInCart;

	   }
	
	public boolean addItemToCart(int orderId, int customerId, int productId, int OrderQty) {

	       String sql = "INSERT INTO orders (orderId, customerId, productId, inCart, orderQty) " +
	                    "VALUES (?, ?, ?, true, ?)";
	       try  {
	    	   Connection cn = Util.getConnection();
	    	   PreparedStatement ps = cn.prepareStatement(sql);
	    	   ps.setInt(1, orderId);
	           ps.setInt(2, customerId);
	           ps.setInt(3, productId);
	           ps.setInt(4, OrderQty);

	           int rowsAffected = ps.executeUpdate();
	           return rowsAffected > 0; 

	       } catch (SQLException e) {

	           e.printStackTrace();

	           return false; 
	       }
	   }
	
	public boolean updateItemInCart(int orderId, int OrderQty) {
	       String sql = "UPDATE Orders SET orderQty = ? WHERE orderId = ?";
	       try (Connection cn = Util.getConnection()) {
	          PreparedStatement ps = cn.prepareStatement(sql);
	           ps.setInt(1, OrderQty);
	           ps.setInt(2, orderId);  
	           int rowsAffected = ps.executeUpdate();
	           System.out.println(rowsAffected);
	           return rowsAffected > 0;
	       } catch (SQLException e) {
	           e.printStackTrace();
	           return false;
	       }

	   }

	public List<OrderResponse> getAllOrders() {
		   ArrayList<OrderResponse> productsInCart = new ArrayList<>();
		   String sql = "SELECT p.productId, p.productName, p.productPrice, p.productCategory, p.productDescription, " +
	            "o.orderId, o.orderQty, o.orderStatus, o.orderDate, o.arrivalDate, o.orderAddress " +
	            "FROM orders o " +
	            "JOIN product p ON o.productId = p.productId where inCart= false";
			try  {
				Connection cn = Util.getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					OrderResponse orderResponse = new OrderResponse(
				       rs.getInt("productId"),              
				       rs.getString("productName"),         
				       rs.getDouble("productPrice"),        
				       rs.getString("productCategory"),     
				       rs.getString("productDescription"),  
				       rs.getInt("orderId"),
				       rs.getInt("orderQty"),               
				       rs.getString("orderStatus"),         
				       rs.getString("orderDate"),           
				       rs.getString("arrivalDate"),         
				       rs.getString("orderAddress"));       
					productsInCart.add(orderResponse);

				}
				} catch (SQLException e) {
			        e.printStackTrace();
			}

		    return productsInCart;

	   }

	public boolean updateOrderStatus(int orderId, String orderStatus) {
	       String sql = "UPDATE Orders SET orderStatus = ? WHERE orderId = ?";
	       try (Connection cn = Util.getConnection()) {
	          PreparedStatement ps = cn.prepareStatement(sql);
	           ps.setString(1, orderStatus);
	           ps.setInt(2, orderId);  
	           int rowsAffected = ps.executeUpdate();
	           return rowsAffected > 0;
	       } catch (SQLException e) {
	           e.printStackTrace();
	           return false;
	       }
	}

	public boolean deleteCartItemById(int orderId, int customerId) {
		 String sql = "DELETE FROM Orders WHERE orderId = ? AND customerId = ?";   
		 try (Connection cn = Util.getConnection()) {
	          PreparedStatement ps = cn.prepareStatement(sql);
	           ps.setInt(1, orderId);
	           ps.setInt(2, customerId);  
	           int rowsAffected = ps.executeUpdate();
	           return rowsAffected > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false; 
	        }
	    }

	public boolean buyItem(Order order) {
	    String sql = "UPDATE Orders SET customerId = ?, productId = ?, orderDate = ?, arrivalDate = ?, "
	               + "orderStatus = ?, inCart = ?, orderAddress = ?, orderQty = ? WHERE orderId = ?";
	    try (Connection cn = Util.getConnection()) {
	        PreparedStatement ps = cn.prepareStatement(sql);
	        ps.setInt(1, order.getCustomerId());
	        ps.setInt(2, order.getProductId());
	        ps.setString(3, order.getOrderDate());
	        ps.setString(4, order.getArrivalDate());
	        ps.setString(5, order.getOrderStatus());
	        ps.setBoolean(6, order.isInCart());
	        ps.setString(7, order.getOrderAddress());
	        ps.setInt(8, order.getOrderQty());
	        ps.setInt(9, order.getOrderId());
	        
	        int rowsUpdated = ps.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean buyNewItem(Order order) {

		   String sql = "INSERT INTO Orders (orderId, customerId, productId, orderDate, arrivalDate, "
		              + "orderStatus, inCart, orderAddress, orderQty) "
		              + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		   try (Connection cn = Util.getConnection()) {
		       PreparedStatement ps = cn.prepareStatement(sql);
		       // Set parameters for the prepared statement
		       ps.setInt(1, order.getOrderId());
		       ps.setInt(2, order.getCustomerId());
		       ps.setInt(3, order.getProductId());
		       ps.setString(4, order.getOrderDate());
		       ps.setString(5, order.getArrivalDate());
		       ps.setString(6, order.getOrderStatus());
		       ps.setBoolean(7, order.isInCart());
		       ps.setString(8, order.getOrderAddress());
		       ps.setInt(9, order.getOrderQty());
		       // Execute the insertion
		       int rowsInserted = ps.executeUpdate();

		       // Return true if the order was successfully inserted
		       return rowsInserted > 0;

		   } catch (Exception e) {

		       e.printStackTrace();

		       return false;

		   }

		}




}
