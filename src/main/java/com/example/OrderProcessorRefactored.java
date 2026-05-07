package com.example;

import java.util.List;
import java.time.LocalDateTime;

public class OrderProcessorRefactored {
    
    public String processOrder(Order order, PaymentDetails paymentDetails) {
        try {
            validateOrderInput(order, paymentDetails);
            double total = calculateOrderTotal(order);
            double discount = applyDiscountIfEligible(total);
            double finalTotal = total - discount;
            
            processPayment(paymentDetails, finalTotal);
            createOrderRecord(order, finalTotal, discount);
            sendOrderConfirmationEmail(order, finalTotal);
            updateInventory(order.getItems());
            
            return "Order processed successfully. Order ID: " + order.getId();
        } catch (Exception e) {
            return "Order processing failed: " + e.getMessage();
        }
    }
    
    private void validateOrderInput(Order order, PaymentDetails paymentDetails) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (order.getId() == null || order.getId() <= 0) {
            throw new IllegalArgumentException("Invalid order ID");
        }
        if (order.getCustomer() == null) {
            throw new IllegalArgumentException("Customer information required");
        }
        validateCustomer(order.getCustomer());
        validateItems(order.getItems());
        validatePaymentDetails(paymentDetails);
    }
    
    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid customer name");
        }
        if (customer.getEmail() == null || !customer.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid customer email");
        }
        if (customer.getShippingAddress() == null || customer.getShippingAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid shipping address");
        }
    }
    
    private void validateItems(List<Item> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("No items in order");
        }
        for (Item item : items) {
            if (item.getQuantity() <= 0) {
                throw new IllegalArgumentException("Invalid quantity for item: " + item.getName());
            }
            if (item.getPrice() <= 0) {
                throw new IllegalArgumentException("Invalid price for item: " + item.getName());
            }
        }
    }
    
    private void validatePaymentDetails(PaymentDetails paymentDetails) {
        if (paymentDetails == null) {
            throw new IllegalArgumentException("Payment details required");
        }
        if (paymentDetails.getMethod() == null || paymentDetails.getMethod().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid payment method");
        }
        
        if (paymentDetails.getMethod().equals("CREDIT_CARD")) {
            validateCreditCardDetails(paymentDetails);
        } else if (!paymentDetails.getMethod().equals("PAYPAL")) {
            throw new IllegalArgumentException("Unsupported payment method");
        }
    }
    
    private void validateCreditCardDetails(PaymentDetails paymentDetails) {
        if (paymentDetails.getCreditCardNumber() == null || paymentDetails.getCreditCardNumber().length() != 16) {
            throw new IllegalArgumentException("Invalid credit card number");
        }
        if (paymentDetails.getExpirationDate() == null || paymentDetails.getExpirationDate().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid expiration date");
        }
        if (paymentDetails.getCvv() == null || paymentDetails.getCvv().length() != 3) {
            throw new IllegalArgumentException("Invalid CVV");
        }
    }
    
    private double calculateOrderTotal(Order order) {
        return order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
    
    private double applyDiscountIfEligible(double total) {
        if (total > 100) {
            return total * 0.1; // 10% discount
        }
        return 0.0;
    }
    
    private void processPayment(PaymentDetails paymentDetails, double amount) {
        boolean paymentSuccessful = false;
        
        if (paymentDetails.getMethod().equals("CREDIT_CARD")) {
            paymentSuccessful = processCreditCardPayment(paymentDetails, amount);
        } else if (paymentDetails.getMethod().equals("PAYPAL")) {
            paymentSuccessful = processPayPalPayment(paymentDetails, amount);
        }
        
        if (!paymentSuccessful) {
            throw new RuntimeException("Payment failed");
        }
    }
    
    private boolean processCreditCardPayment(PaymentDetails paymentDetails, double amount) {
        // Simulate credit card payment processing
        System.out.println("Processing credit card payment of $" + String.format("%.2f", amount));
        return true;
    }
    
    private boolean processPayPalPayment(PaymentDetails paymentDetails, double amount) {
        // Simulate PayPal payment processing
        System.out.println("Processing PayPal payment of $" + String.format("%.2f", amount));
        return true;
    }
    
    private void createOrderRecord(Order order, double total, double discount) {
        String orderRecord = String.format(
            "Order ID: %d, Customer: %s, Email: %s, Address: %s, Total: $%.2f, Discount: $%.2f, Payment: %s, Date: %s",
            order.getId(),
            order.getCustomer().getName(),
            order.getCustomer().getEmail(),
            order.getCustomer().getShippingAddress(),
            total,
            discount,
            order.getPaymentDetails().getMethod(),
            LocalDateTime.now()
        );
        
        System.out.println("Order record created: " + orderRecord);
    }
    
    private void sendOrderConfirmationEmail(Order order, double total) {
        Customer customer = order.getCustomer();
        String emailSubject = "Order Confirmation - #" + order.getId();
        String emailBody = buildEmailBody(customer, order, total);
        
        System.out.println("Email sent to " + customer.getEmail());
        System.out.println("Subject: " + emailSubject);
        System.out.println("Body: " + emailBody);
    }
    
    private String buildEmailBody(Customer customer, Order order, double total) {
        return String.format(
            "Dear %s,\n\n" +
            "Thank you for your order!\n\n" +
            "Order Details:\n" +
            "Order ID: %d\n" +
            "Total Amount: $%.2f\n" +
            "Payment Method: %s\n\n" +
            "Your order will be shipped to:\n" +
            "%s\n\n" +
            "Thank you for shopping with us!",
            customer.getName(),
            order.getId(),
            total,
            order.getPaymentDetails().getMethod(),
            customer.getShippingAddress()
        );
    }
    
    private void updateInventory(List<Item> items) {
        for (Item item : items) {
            System.out.println("Updating inventory for " + item.getName() + 
                             ", quantity: " + item.getQuantity());
        }
    }
}
