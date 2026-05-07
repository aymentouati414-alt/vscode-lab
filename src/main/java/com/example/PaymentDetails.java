package com.example;

public class PaymentDetails {
    private String method;
    private String creditCardNumber;
    private String expirationDate;
    private String cvv;
    private String paypalEmail;
    private double amount;
    private String transactionId;
    
    public PaymentDetails() {
    }
    
    public PaymentDetails(String method) {
        this.method = method;
    }
    
    public PaymentDetails(String method, String creditCardNumber, String expirationDate, String cvv) {
        this.method = method;
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }
    
    public PaymentDetails(String method, String paypalEmail) {
        this.method = method;
        this.paypalEmail = paypalEmail;
    }
    
    // Getters and Setters
    public String getMethod() {
        return method;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }
    
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    
    public String getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public String getCvv() {
        return cvv;
    }
    
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
    public String getPaypalEmail() {
        return paypalEmail;
    }
    
    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    @Override
    public String toString() {
        return "PaymentDetails{" +
                "method='" + method + '\'' +
                ", creditCardNumber='" + (creditCardNumber != null ? "****-****-****-" + creditCardNumber.substring(12) : "null") + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvv='" + (cvv != null ? "***" : "null") + '\'' +
                ", paypalEmail='" + paypalEmail + '\'' +
                ", amount=" + amount +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
