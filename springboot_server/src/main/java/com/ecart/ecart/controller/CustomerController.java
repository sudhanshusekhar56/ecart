package com.ecart.ecart.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecart.ecart.bean.Customer;
import com.ecart.ecart.bean.Order;
import com.ecart.ecart.bean.OrderResponse;

import com.ecart.ecart.service.CustomerService;
import com.ecart.ecart.util.generateId;
import com.google.gson.Gson;

@RestController


@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "*"})
public class CustomerController {
    CustomerService customerService = new CustomerService();

    @PostMapping("/login")
    public ResponseEntity<String> validateCustomer(@RequestBody Map<String, String> credentials) {
        Gson gson = new Gson();
        try {
            String email = credentials.get("username");
            String password = credentials.get("password");
            Customer customer = customerService.validateCustomer(email, password);
            if (customer != null) {
                String jsonResponse = gson.toJson(customer);
                return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
            } else {
                String jsonResponse = gson.toJson("Invalid email or password.");
                return new ResponseEntity<>(jsonResponse, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String jsonResponse = gson.toJson("An error occurred during login.");
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
    	Gson gson = new Gson();
        try {
        	int generatedId = generateId.getId(3);
            customer.setCustomerId(generatedId);
            boolean isAdded = customerService.addCustomer(customer);
            if (isAdded) {
                String jsonResponse = gson.toJson(customer);
                return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
            } else {
                String jsonResponse = gson.toJson("Failed to register. Please try again.");
                return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String jsonResponse = gson.toJson("An error occurred while registering.");
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/customer/updateProfile")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        Gson gson = new Gson();
        try {
            boolean isUpdated = customerService.updateCustomerProfile(customer);
            if (isUpdated) {
                String jsonResponse = gson.toJson("Customer profile updated successfully!");
                return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
            } else {
                String jsonResponse = gson.toJson("Failed to update customer profile. Please try again.");
                return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String jsonResponse = gson.toJson("An error occurred while updating the profile.");
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/customer/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestBody) {
        Gson gson = new Gson();
        try {
            // Extracting data from the request body
            int customerId = Integer.parseInt(requestBody.get("customerId"));
            String oldPassword = requestBody.get("oldPassword");
            String newPassword = requestBody.get("newPassword");

            String getOldPassword = customerService.getOldPassword(customerId);
            if(getOldPassword.equalsIgnoreCase(oldPassword)) {
                boolean isUpdated = customerService.changePassword(customerId, newPassword);

                if (isUpdated) {
                    String jsonResponse = gson.toJson("Password updated successfully!");
                    return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
                } else {
                    String jsonResponse = gson.toJson("Failed to update password. Please try again.");
                    return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
                }
            }
            else {
            	String jsonResponse = gson.toJson("Old password did not match.");
                return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
            String jsonResponse = gson.toJson("An error occurred while changing the password.");
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    
    @GetMapping("/customer/cart/{customerId}")

    public ResponseEntity<String> getCartItems(@PathVariable int customerId) {
        List<OrderResponse> cartItems = customerService.getCartItemsForCustomer(customerId);

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(cartItems);

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

    }
    
    @PostMapping("/customer/addCart")
    public ResponseEntity<String> addItemToCart(@RequestBody Map<String, Object> requestBody) {
    	
        int customerId = (int) requestBody.get("customerId");
        int productId = (int) requestBody.get("productId");
        int orderQty = (int) requestBody.get("orderQty");
        int orderId = generateId.getId(3); 
        boolean success = customerService.addItemToCart(orderId, customerId, productId, orderQty);

        if (success) {
            String jsonResponse = String.format("{\"message\":\"Item added to cart successfully with product Id: %d\"}", productId);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        } else {
            String jsonResponse = "{\"message\":\"Failed to add item to cart.\"}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/customer/updateCart")
    public ResponseEntity<String> updateItemInCart(@RequestBody Map<String, Object> requestBody) {
    	int orderId = (int) requestBody.get("orderId");
        int orderQty = (int) requestBody.get("orderQty");
        
        boolean success = customerService.updateItemInCart(orderId, orderQty);
        String jsonResponse;

        if (success) {
            jsonResponse = "{\"message\":\"Item quantity updated successfully in cart with Item ID: " + orderId + "\"}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        } else {
            jsonResponse = "{\"message\":\"Failed to update item in cart. Please check if the item exists in the cart.\"}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/customer/deleteCartItem")
    public ResponseEntity<String> deleteCartItemById(@RequestBody Map<String, Object> requestBody) {
    	int orderId = (int) requestBody.get("orderId");
        int customerId = (int) requestBody.get("customerId");
        
        boolean success = customerService.deleteCartItemById(orderId, customerId);
        String jsonResponse;

        if (success) {
            jsonResponse = "{\"message\":\"Item successfully deleted from the cart with Item ID: " + orderId + "\"}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        } else {
            jsonResponse = "{\"message\":\"Failed to delete item from cart. Please check if the item exists in the cart.\"}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/customer/buyItem/{customerId}")
    public ResponseEntity<String> buyItem(@RequestBody Order order, @PathVariable int customerId) {
    	System.out.println(order);
        order.setCustomerId(customerId);
        // Set the current date as orderDate in yyyy-MM-dd format
        LocalDate orderDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        order.setOrderDate(orderDate.format(formatter));

        // Calculate a random arrival date (5 to 10 days from orderDate)
        Random random = new Random();
        int daysToAdd = 5 + random.nextInt(6); // Random number between 5 and 10
        LocalDate arrivalDate = orderDate.plusDays(daysToAdd);
        order.setArrivalDate(arrivalDate.format(formatter));

        // Set inCart to false and orderStatus to Confirmed
        order.setInCart(false);
        order.setOrderStatus("Confirmed");

        // Simulate saving the order or processing logic here
        System.out.println(order);
        boolean success = customerService.buyItem(order);
        String jsonResponse;
        Gson gson = new Gson();
        if (success) {
        	 jsonResponse =  gson.toJson(order);
             return new ResponseEntity<>(jsonResponse, HttpStatus.ACCEPTED);
        } else {
            jsonResponse = "{\"message\":\"Could not complete the process.\"}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
        }   

    }
    
    @PostMapping("/customer/buyNewItem")
    public ResponseEntity<String> buyNewItem(@RequestBody Order order) {
        int oId = generateId.getId(3);
        order.setOrderId(oId);
        // Set the current date as orderDate in yyyy-MM-dd format
        LocalDate orderDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        order.setOrderDate(orderDate.format(formatter));

        // Calculate a random arrival date (5 to 10 days from orderDate)
        Random random = new Random();
        int daysToAdd = 5 + random.nextInt(6); // Random number between 5 and 10
        LocalDate arrivalDate = orderDate.plusDays(daysToAdd);
        order.setArrivalDate(arrivalDate.format(formatter));

        // Set inCart to false and orderStatus to Confirmed
        order.setInCart(false);
        order.setOrderStatus("Confirmed");
        System.out.println(order);
        // Simulate saving the order or processing logic here
        
        boolean success = customerService.buyNewItem(order);
        String jsonResponse;
        Gson gson = new Gson();
        if (success) {
        	 jsonResponse =  gson.toJson(order);
             return new ResponseEntity<>(jsonResponse, HttpStatus.ACCEPTED);
        } else {
            jsonResponse = "{\"message\":\"Could not complete the process.\"}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
        }   

    }

    
    @GetMapping("/customer/orders/{customerId}")
    public ResponseEntity<String> getOrderItems(@PathVariable int customerId) {
        List<OrderResponse> cartItems = customerService.getOrderItemsForCustomer(customerId);
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(cartItems);

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

    }
    
    @DeleteMapping("/customer/orders/{orderId}/{customerId}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable int orderId, @PathVariable int customerId) {
        boolean response = customerService.deleteOrderItem(orderId, customerId);
        Gson gson = new Gson();
        if(response) {        	
        	String jsonResponse = gson.toJson("Order with Order No "+ orderId+" cancelled successfully.");
        	return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }
        else {
        	String jsonResponse = gson.toJson("Unable to cancel the order.");
        	return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
        }
    }

}



