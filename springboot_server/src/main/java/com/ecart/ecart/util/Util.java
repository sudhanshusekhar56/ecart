package com.ecart.ecart.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {
	public static Connection getConnection() {
		Connection cn=null;
		try {
			String driver="org.apache.derby.jdbc.EmbeddedDriver";
		String databaseURL = "jdbc:derby:/home/nexus/dev/ecart-mavenUpdated/ecart-maven/EcartDb;create=true";
		Class.forName(driver);
		cn=DriverManager.getConnection(databaseURL);
	} catch(ClassNotFoundException e) {
		System.out.println(e.getMessage());
	} catch(SQLException e) {
		System.out.println(e.getMessage());
	}
	return cn;
	}
	
	
	public static void closeAllConnection(Connection cn, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(cn!=null) {
				cn.close();
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void main(String[] args) {
		try {
			Connection cn=Util.getConnection();
			PreparedStatement ps=null;
			int t=0;
			String sqlQuery1 = "CREATE TABLE Customer (customerId INT PRIMARY KEY, customerName VARCHAR(50),customerEmail VARCHAR(100) NOT NULL,customerAddress VARCHAR(100),customerPassword VARCHAR(30) CHECK (LENGTH(customerPassword) >= 10 AND LENGTH(customerPassword) <= 30),isAdmin BOOLEAN NOT NULL)"; 
			ps=cn.prepareStatement(sqlQuery1);
			t=ps.executeUpdate();
			ps=cn.prepareStatement("INSERT INTO Customer VALUES(101,'John Doe', 'john.doe@example.com', '123 Main St, Springfield', 'Password1234',FALSE)");
			t=ps.executeUpdate();
			ps=cn.prepareStatement("INSERT INTO Customer VALUES(102,'Jane Smith', 'jane.smith@example.com', '456 Oak Rd, Rivertown', 'SecureP@ss123',FALSE)");
			t=ps.executeUpdate();
			ps=cn.prepareStatement("INSERT INTO Customer VALUES(103,'Michael Johnson', 'michael.johnson@example.com', '789 Elm St, Lakedale', 'MyStrongPassword1',FALSE)");
			t=ps.executeUpdate();
			ps=cn.prepareStatement("INSERT INTO Customer VALUES(104,'Emily Davis', 'emily.davis@example.com', '321 Pine Ave, Hillside', 'ComplexPasswd2024',FALSE)");
			t=ps.executeUpdate();
			ps=cn.prepareStatement("INSERT INTO Customer VALUES(105,'David Martinez', 'david.martinez@example.com', '654 Maple Dr, Sunnyville', 'D@vid12345',FALSE)");
			t=ps.executeUpdate();
			ps=cn.prepareStatement("INSERT INTO Customer VALUES(106,'Sarah Wilson', 'sarah.wilson@example.com', '987 Birch Blvd, Crestwood', 'Passw0rd789',FALSE)");
			t=ps.executeUpdate();
			ps=cn.prepareStatement("INSERT INTO Customer VALUES(107,'Chris Brown', 'chris.brown@example.com', '432 Cedar Ln, Westfield', 'C@seSensitive2023',FALSE)");
			t=ps.executeUpdate();
			ps=cn.prepareStatement("INSERT INTO Customer VALUES(108,'Linda Taylor', 'linda.taylor@example.com', '876 Willow Way, Greenfield', 'SimplePassword567',FALSE)");
			t=ps.executeUpdate();
			ps=cn.prepareStatement("INSERT INTO Customer VALUES(109,'Robert Lee', 'robert.lee@example.com', '123 Redwood Rd, Fairview', 'R@bobPass987',FALSE)");
			t=ps.executeUpdate();
			ps=cn.prepareStatement("INSERT INTO Customer VALUES(110,'Admin', 'admin@example.com', '543 Spruce St, Brooktown', 'AdminP@ssword88',TRUE)");
			t=ps.executeUpdate();
		    System.out.println(t);
			ps=cn.prepareStatement("SELECT * FROM Customer");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getString(4)+rs.getString(5));
			}
			rs.close();
			
			int t2=0;
			String sqlQuery3 = "Create table Product(productId int not null primary key, productName varchar(150), productPrice double, productCategory varchar(50), productDescription varchar(200), isAvailable boolean)";
			ps = cn.prepareStatement(sqlQuery3);
			t2 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Product values(1, 'Smartphone', 599.99, 'Electronics', 'Latest model with advanced features', true)");
			t2 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Product values(2, 'Laptop', 1299.50, 'Electronics', 'High-performance laptop for professionals', true)");
			t2 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Product values(3, 'Desk Chair', 199.99, 'Furniture', 'Ergonomic chair with lumbar support', true)");
			t2 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Product values(4, 'Electric Kettle', 49.99, 'Appliances', '1.7L capacity with auto shut-off feature', true)");
			t2 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Product values(5, 'Gaming Console', 499.00, 'Gaming', 'Next-gen gaming console with 4K support', true)");
			t2 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Product values(6, 'Headphones', 149.99, 'Accessories', 'Noise-canceling over-ear headphones', true)");
			t2 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Product values(7, 'Refrigerator', 999.00, 'Appliances', 'Energy-efficient double-door refrigerator', true)");
			t2 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Product values(8, 'Tablet', 349.99, 'Electronics', 'Compact tablet with 10-inch display', true)");
			t2 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Product values(9, 'Bookshelf', 89.99, 'Furniture', 'Modern wooden bookshelf with 5 shelves', true)");
			t2 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Product values(10, 'Smartwatch', 199.99, 'Accessories', 'Fitness-focused smartwatch with GPS', true)");
			t2 = ps.executeUpdate();
		
			System.out.println(t2);
			ps=cn.prepareStatement("SELECT * FROM Product");
			ResultSet rs2=ps.executeQuery();
			while(rs2.next()) {
				System.out.println(rs2.getInt(1)+":"+rs2.getString(2)+":"+rs2.getDouble(3)+":"+rs2.getString(4)+":"+rs2.getString(5)+":"+rs2.getBoolean(6));
			}
		    rs2.close();
		    
		    int t1 = 0;
			String sqlQuery2 = "Create table Orders (orderId int not null primary key, customerId int, productId int, orderDate varchar(20), arrivalDate varchar(20), orderStatus varchar(100), orderAddress varchar(100), orderQty int, inCart boolean, foreign key(customerId) references Customer(customerId), foreign key(productId) references Product(productId))";
		    ps = cn.prepareStatement(sqlQuery2);
		    t1 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Orders values(1, 101, 1, '2024-11-01', '2024-11-05', 'Delivered', '123 Green Lane, City A', 2, false)");
			t1 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Orders values(2, 102, 2, '2024-11-02', '2024-11-06', 'InTransit', '456 Blue Road, City B', 1, false)");
			t1 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Orders values(3, 103, 3, '2024-11-03', '2024-11-07', 'Confirmed', '789 Yellow Street, City C', 3, false)");
			t1 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Orders values(4, 104, 1, '2024-11-04', '2024-11-08', 'Delivered', '321 Orange Avenue, City D', 5, false)");
			t1 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Orders values(5, 105, 9, '2024-11-05', '2024-11-09', 'Returned', '654 Red Boulevard, City E', 4, false)");
			t1 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Orders values(6, 106, 4, '2024-11-06', '2024-11-10', 'Returned', '987 Purple Plaza, City F', 6, false)");
			t1 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Orders values(7, 107, 5, '2024-11-07', '2024-11-11', 'Delivered', '147 Silver Lane, City G', 7, false)");
			t1 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Orders values(8, 108, 7, '2024-11-08', '2024-11-12', 'InTransit', '258 Gold Street, City H', 8, false)");
			t1 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Orders values(9, 109, 6, '2024-11-09', '2024-11-13', 'Confirmed', '369 Platinum Road, City I', 9, false)");
			t1 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Orders values(10, 108, 8, '2024-11-10', '2024-11-14', 'Delivered', '753 Diamond Court, City J', 10, false)");
			t1 = ps.executeUpdate();
			ps = cn.prepareStatement("insert into Orders values(11, 110, 8, '2024-11-10', '2024-11-14', 'Delivered', '753 Diamond Court, City J', 10, true)");
			t1 = ps.executeUpdate();
			
			System.out.println(t1);
			ps=cn.prepareStatement("SELECT * FROM Orders");
			ResultSet rs1=ps.executeQuery();
			while(rs1.next()) {
				System.out.println(rs1.getInt(1)+":"+rs1.getInt(2)+":"+rs1.getInt(3)+":"+rs1.getString(4)+":"+rs1.getString(5)+":"+rs1.getString(6)+":"+rs1.getString(7)+":"+rs1.getString(8));
			}
			rs1.close();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
