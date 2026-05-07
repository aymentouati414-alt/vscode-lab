package com.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Order {
    private Long id;
    private Customer customer;
    private List<Item> items;
    private PaymentDetails paymentDetails;
    private LocalDateTime orderDate;
    private LocalDateTime shippingDate;
    private String orderStatus;
    private String trackingNumber;
    
    public Order() {
        this.items = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.orderStatus = "PENDING";
    }
    
    public Order(Long id, Customer customer) {
        this();
        this.id = id;
        this.customer = customer;
    }
    
    public Order(Long id, Customer customer, List<Item> items, PaymentDetails paymentDetails) {
        this(id, customer);
        this.items = items != null ? items : new ArrayList<>();
        this.paymentDetails = paymentDetails;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public void setItems(List<Item> items) {
        this.items = items != null ? items : new ArrayList<>();
    }
    
    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }
    
    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
    
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    
    public LocalDateTime getShippingDate() {
        return shippingDate;
    }
    
    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }
    
    public String getOrderStatus() {
        return orderStatus;
    }
    
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public String getTrackingNumber() {
        return trackingNumber;
    }
    
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
    
    // Business methods
    public void addItem(Item item) {
        if (item != null) {
            this.items.add(item);
        }
    }
    
    public void removeItem(Item item) {
        if (item != null) {
            this.items.remove(item);
        }
    }
    
    public void removeItem(Long itemId) {
        this.items.removeIf(item -> item.getId().equals(itemId));
    }
    
    public double calculateTotal() {
        return items.stream()
                .mapToDouble(Item::getTotalPrice)
                .sum();
    }
    
    public int getTotalItems() {
        return items.stream()
                .mapToInt(Item::getQuantity)
                .sum();
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public void markAsShipped() {
        this.orderStatus = "SHIPPED";
        this.shippingDate = LocalDateTime.now();
    }
    
    public void markAsDelivered() {
        this.orderStatus = "DELIVERED";
    }
    
    public void markAsCancelled() {
        this.orderStatus = "CANCELLED";
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", items=" + items +
                ", paymentDetails=" + paymentDetails +
                ", orderDate=" + orderDate +
                ", shippingDate=" + shippingDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                '}';
    }
}
