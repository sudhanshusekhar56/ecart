package com.ecart.ecart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ecart.ecart.bean.Customer;
import com.ecart.ecart.util.Util;

public class CustomerDao {
	   public ArrayList<Customer> getCustomers(){
	        ArrayList<Customer> list = new ArrayList<>();
	        String sql = "SELECT * FROM Customer";
	        try { 
	        	Connection cn = Util.getConnection();
	        	PreparedStatement ps = cn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                int Cid = rs.getInt("customerId");
	                String name = rs.getString("customerName");
	                String email = rs.getString("customerEmail");
	                String address = rs.getString("customerAddress");
	                String password = rs.getString("customerPassword");
	                boolean isAdmin = rs.getBoolean("isAdmin");
	                list.add(new Customer(Cid, name, email, address, password, isAdmin));
	            }
	            Util.closeAllConnection(cn, ps, rs);
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }
	        return list;
	}

	public boolean deleteCustomerById(int id) {
		int res = 0;
		 String sql = "Delete from customer where customerId = ?";
		 try {
			Connection cn = Util.getConnection();
	        PreparedStatement ps = cn.prepareStatement(sql);
	        ps.setInt(1, id);
	        res = ps.executeUpdate();
	        
		 }
		 catch(SQLException e) {
			 e.printStackTrace();
		 }
		return res>0;
	}
	
	public boolean addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer (customerId, customerName, customerEmail, customerAddress, customerPassword, isAdmin) VALUES (?, ?, ?, ?, ?, ?)";
        try  {
        	Connection cn = Util.getConnection();
        	PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, customer.getCustomerId());
            ps.setString(2, customer.getCustomerName());
            ps.setString(3, customer.getCustomerEmail());
            ps.setString(4, customer.getCustomerAddress());
            ps.setString(5, customer.getCustomerPassword());
            ps.setBoolean(6, customer.isAdmin());
            return ps.executeUpdate() > 0;
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return false;
    }
	
	public boolean updateCustomerProfile(Customer customer) {
	    String query = "UPDATE customer SET customerName = ?, customerEmail = ?, customerAddress = ? WHERE customerId = ?";
	    try (Connection connection = Util.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        statement.setString(1, customer.getCustomerName());
	        statement.setString(2, customer.getCustomerEmail());
	        statement.setString(3, customer.getCustomerAddress());
	        statement.setInt(4, customer.getCustomerId());

	        int rowsUpdated = statement.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	
	public static Customer validateCustomer(String email, String password) {
        String sql = "SELECT * FROM Customer WHERE customerEmail = ? AND customerPassword = ?";
        try  {
        	Connection cn = Util.getConnection();
        	PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(rs.getInt("customerId"));
                    customer.setCustomerName(rs.getString("customerName"));
                    customer.setCustomerEmail(rs.getString("customerEmail"));
                    customer.setCustomerAddress(rs.getString("customerAddress"));
                    customer.setCustomerPassword(rs.getString("customerPassword"));
                    customer.setAdmin(rs.getBoolean("isAdmin"));
                    return customer;
                }
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return null;
        
    }

	public boolean changePassword(int customerId, String newPassword) {
	    String query = "UPDATE customer SET customerPassword = ? WHERE customerId = ?";
	    try (Connection connection = Util.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        statement.setString(1, newPassword);
	        statement.setInt(2, customerId);

	        int rowsUpdated = statement.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
		
	}

	public String getOldPassword(int customerId) {
		 String query = "Select customerPassword from Customer where customerId = ?";
		 try (Connection connection = Util.getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {
		        statement.setInt(1, customerId);
		        try (ResultSet rs = statement.executeQuery()) {
		            if (rs.next()) {
		                return rs.getString("customerPassword");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return null;
	}

}
