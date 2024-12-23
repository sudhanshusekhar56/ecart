package com.ecart.ecart.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecart.ecart.bean.Customer;
import com.ecart.ecart.bean.OrderResponse;
import com.ecart.ecart.bean.Product;
import com.ecart.ecart.dao.ProductDao;
import com.ecart.ecart.service.AdminService;
import com.ecart.ecart.util.generateId;
import com.google.gson.Gson;

@RestController

@RequestMapping("api/admin")
@CrossOrigin(origins = {"http://localhost:4200", "*"})
public class AdminController {
   AdminService adminService = new AdminService();

   @GetMapping("/customers")
   public ResponseEntity<String> getAllCustomers() {
	   ArrayList<Customer> customers = adminService.getCustomers();
	   Gson gson = new Gson();
	   String jsonResponse = gson.toJson(customers);
       return new ResponseEntity<>(jsonResponse,HttpStatus.OK);
   }
   
   @DeleteMapping("/customers/{id}")
   public ResponseEntity<String> deleteCustomerById(@PathVariable int id) {
       boolean isDeleted = adminService.deleteCustomerById(id);
       Gson gson = new Gson();
       if (isDeleted) {
           String jsonResponse = gson.toJson("Customer with ID " + id + " deleted successfully.");
           return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
       } else {
           String jsonResponse = gson.toJson("Customer with ID " + id + " not found.");
           return new ResponseEntity<>(jsonResponse, HttpStatus.NOT_FOUND);
       }
   }
   
   @PostMapping("/products")
   public ResponseEntity<String> addProduct(@RequestBody Product product) {
	   Gson gson = new Gson();
       try {
           int productId = generateId.getId(3);
           product.setProductId(productId);
           boolean isAdded = ProductDao.addProduct(product);

           if (isAdded) {
               String jsonResponse = gson.toJson("Product added successfully with ID: " + productId);
               return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
           } else {
               String jsonResponse = gson.toJson("Failed to add product.");
               return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
           }
       } catch (Exception e) {
           e.printStackTrace();
           String jsonResponse = gson.toJson("An error occurred while adding the product.");
           return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
   
   @GetMapping("/products")
   public ResponseEntity<String> getAllProducts() {
	   ArrayList<Product> products = adminService.getProducts();
	   Gson gson = new Gson();
	   String jsonResponse = gson.toJson(products);
       return new ResponseEntity<>(jsonResponse,HttpStatus.OK);
   }
   
   
   @DeleteMapping("/products/{id}")
   public ResponseEntity<String> deleteProductById(@PathVariable int id) {
	   System.out.println();
       boolean isDeleted = adminService.deleteProductById(id);
       Gson gson = new Gson();
       if (isDeleted) {
           String jsonResponse = gson.toJson("Customer with ID " + id + " deleted successfully.");
           return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
       } else {
           String jsonResponse = gson.toJson("Customer with ID " + id + " not found.");
           return new ResponseEntity<>(jsonResponse, HttpStatus.NOT_FOUND);
       }
   }
   
   @GetMapping("/orders")
   public ResponseEntity<String> getAllOrders() {
       List<OrderResponse> orders = adminService.getAllOrdres();
       Gson gson = new Gson();
       String jsonResponse = gson.toJson(orders);

       return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
   }
   
   @PutMapping("/orders/{id}/{orderStatus}")
   public ResponseEntity<String> updateOrderStatus(@PathVariable("id") int orderId, @PathVariable String orderStatus){
	   System.out.println(orderStatus);
       boolean success = adminService.updateOrderStatus(orderId, orderStatus);
       String jsonResponse;

       if (success) {
           jsonResponse = "{\"message\":\"Order updated successfully with id: " + orderId + "\"}";
           return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

       } else {
           jsonResponse = "{\"message\":\"Failed to update item in cart. Please check if the item exists in the cart.\"}";
           return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);

       }
   }
   
   @PutMapping("/products/{productId}")
   public ResponseEntity<String> updateProductData(@RequestBody Product product){
       String jsonResponse;
	   boolean success = adminService.updateProductData(product);
       if (success) {
           jsonResponse = "{\"message\":\"Product updated successfully with id: " + product.getProductId() + "\"}";
           return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

       } else {
           jsonResponse = "{\"message\":\"Failed to update product details.\"}";
           return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);

       }
   }

}