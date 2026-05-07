package com.example;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class OrderProcessor {
    
    // LONG METHOD - This method does too many things and needs to be refactored
    public String processOrder(Long orderId, String customerName, String customerEmail, 
                             String shippingAddress, List<String> itemNames, 
                             List<Double> itemPrices, List<Integer> quantities,
                             String paymentMethod, String creditCardNumber, 
                             String expirationDate, String cvv) {
        
        // Step 1: Validate input
        if (orderId == null || orderId <= 0) {
            return "Invalid order ID";
        }
        if (customerName == null || customerName.trim().isEmpty()) {
            return "Invalid customer name";
        }
        if (customerEmail == null || !customerEmail.contains("@")) {
            return "Invalid customer email";
        }
        if (shippingAddress == null || shippingAddress.trim().isEmpty()) {
            return "Invalid shipping address";
        }
        if (itemNames == null || itemNames.isEmpty()) {
            return "No items in order";
        }
        if (itemPrices == null || itemPrices.size() != itemNames.size()) {
            return "Invalid item prices";
        }
        if (quantities == null || quantities.size() != itemNames.size()) {
            return "Invalid quantities";
        }
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            return "Invalid payment method";
        }
        
        // Step 2: Calculate total
        double total = 0.0;
        for (int i = 0; i < itemNames.size(); i++) {
            if (quantities.get(i) <= 0) {
                return "Invalid quantity for item: " + itemNames.get(i);
            }
            if (itemPrices.get(i) <= 0) {
                return "Invalid price for item: " + itemNames.get(i);
            }
            total += itemPrices.get(i) * quantities.get(i);
        }
        
        // Step 3: Apply discount if total > 100
        double discount = 0.0;
        if (total > 100) {
            discount = total * 0.1; // 10% discount
            total -= discount;
        }
        
        // Step 4: Process payment
        boolean paymentSuccessful = false;
        if (paymentMethod.equals("CREDIT_CARD")) {
            if (creditCardNumber == null || creditCardNumber.length() != 16) {
                return "Invalid credit card number";
            }
            if (expirationDate == null || expirationDate.trim().isEmpty()) {
                return "Invalid expiration date";
            }
            if (cvv == null || cvv.length() != 3) {
                return "Invalid CVV";
            }
            // Simulate payment processing
            paymentSuccessful = true;
        } else if (paymentMethod.equals("PAYPAL")) {
            // Simulate PayPal payment
            paymentSuccessful = true;
        } else {
            return "Unsupported payment method";
        }
        
        if (!paymentSuccessful) {
            return "Payment failed";
        }
        
        // Step 5: Create order record
        String orderRecord = "Order ID: " + orderId + 
                           ", Customer: " + customerName + 
                           ", Email: " + customerEmail + 
                           ", Address: " + shippingAddress + 
                           ", Total: $" + String.format("%.2f", total) +
                           ", Discount: $" + String.format("%.2f", discount) +
                           ", Payment: " + paymentMethod +
                           ", Date: " + LocalDateTime.now();
        
        // Step 6: Send confirmation email
        String emailSubject = "Order Confirmation - #" + orderId;
        String emailBody = "Dear " + customerName + ",\n\n" +
                          "Thank you for your order!\n\n" +
                          "Order Details:\n" +
                          "Order ID: " + orderId + "\n" +
                          "Total Amount: $" + String.format("%.2f", total) + "\n" +
                          "Payment Method: " + paymentMethod + "\n\n" +
                          "Your order will be shipped to:\n" +
                          shippingAddress + "\n\n" +
                          "Thank you for shopping with us!";
        
        // Simulate sending email
        System.out.println("Email sent to " + customerEmail);
        System.out.println("Subject: " + emailSubject);
        System.out.println("Body: " + emailBody);
        
        // Step 7: Update inventory
        for (int i = 0; i < itemNames.size(); i++) {
            System.out.println("Updating inventory for " + itemNames.get(i) + 
                             ", quantity: " + quantities.get(i));
        }
        
        return "Order processed successfully. Order ID: " + orderId;
    }
}
