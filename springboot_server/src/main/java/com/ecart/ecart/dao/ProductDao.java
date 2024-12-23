package com.ecart.ecart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ecart.ecart.bean.Product;
import com.ecart.ecart.util.Util;

public class ProductDao {

	public ArrayList<Product> getProducts(){
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try { 
        	Connection cn = Util.getConnection();
        	PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String productName = rs.getString("productName");
                double prouctPrice = rs.getDouble("productPrice");
                String productCategory = rs.getString("productCategory");
                String productDescription = rs.getString("productDescription");
                boolean isAvailable = rs.getBoolean("isAvailable");
              
                list.add(new Product(productId, productName, prouctPrice, productCategory, productDescription, isAvailable));
            }
            Util.closeAllConnection(cn, ps, rs);
    }catch(SQLException e) {
    	e.printStackTrace();
    }
        return list;
}

	public boolean deleteProductById(int id) {
		int res = 0;
		 String sql = "Update product set isAvailable = false where productId = ?";
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
	
	public static boolean addProduct(Product product) {
		int res = 0;
		String sql = "INSERT INTO Product VALUES(?, ?, ?, ?, ?, ?)";
		try {
			Connection cn = Util.getConnection();
	        PreparedStatement ps = cn.prepareStatement(sql);
	        ps.setInt(1, product.getProductId());
            ps.setString(2, product.getProductName());
            ps.setDouble(3, product.getProductPrice());
            ps.setString(4, product.getProductCategory());
            ps.setString(5, product.getProductDescription());
            ps.setBoolean(6, true);
            
	        res = ps.executeUpdate();
		 }
		 catch(SQLException e) {
			 e.printStackTrace();
		 }
		return res>0;
	}

	public boolean updateProductData(Product product) {
	    int res = 0;
	    String sql = "UPDATE Product SET productName = ?, productPrice = ?, productCategory = ?, productDescription = ? WHERE productId = ?";
	    try {
	        Connection cn = Util.getConnection();
	        PreparedStatement ps = cn.prepareStatement(sql);
	        ps.setString(1, product.getProductName());
	        ps.setDouble(2, product.getProductPrice());
	        ps.setString(3, product.getProductCategory());
	        ps.setString(4, product.getProductDescription());
	        ps.setInt(5, product.getProductId());
	        
	        res = ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return res > 0;
	}

	
}
