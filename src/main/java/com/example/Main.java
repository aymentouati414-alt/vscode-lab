package com.example;

/**
 * Main class to demonstrate the Java project examples
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Java Project Examples ===\n");
        
        // Demonstrate Calculator examples
        demonstrateCalculatorExamples();
        
        // Demonstrate Order Processing examples
        demonstrateOrderProcessingExamples();
        
        System.out.println("\n=== All examples completed successfully! ===");
    }
    
    private static void demonstrateCalculatorExamples() {
        System.out.println("1. CALCULATOR EXAMPLES");
        System.out.println("=====================");
        
        // Bad naming example
        System.out.println("Bad Naming (Calc.java):");
        System.out.println("2 + 3 = " + Calc.x(2, 3));
        System.out.println("5 - 3 = " + Calc.y(5, 3));
        System.out.println("2 * 3 = " + Calc.z(2, 3));
        System.out.println("6 / 3 = " + Calc.w(6, 3));
        
        System.out.println("\nGood Naming (Calculator.java):");
        System.out.println("2 + 3 = " + Calculator.add(2, 3));
        System.out.println("5 - 3 = " + Calculator.subtract(5, 3));
        System.out.println("2 * 3 = " + Calculator.multiply(2, 3));
        System.out.println("6 / 3 = " + Calculator.divide(6, 3));
        System.out.println("2^3 = " + Calculator.power(2, 3));
        System.out.println("√16 = " + Calculator.squareRoot(16));
        
        System.out.println();
    }
    
    private static void demonstrateOrderProcessingExamples() {
        System.out.println("2. ORDER PROCESSING EXAMPLES");
        System.out.println("============================");
        
        // Create test data
        Customer customer = new Customer(1L, "John Doe", "john@example.com", "123 Main St");
        Item laptop = new Item(1L, "Laptop", "High-performance laptop", 999.99, 1, "Electronics", "LAPTOP-001");
        Item mouse = new Item(2L, "Mouse", "Wireless mouse", 29.99, 2, "Electronics", "MOUSE-002");
        
        Order order = new Order(1001L, customer);
        order.addItem(laptop);
        order.addItem(mouse);
        
        PaymentDetails paymentDetails = new PaymentDetails("CREDIT_CARD", "1234567890123456", "12/25", "123");
        order.setPaymentDetails(paymentDetails);
        
        // Long method example
        System.out.println("Long Method (OrderProcessor.java):");
        OrderProcessor longProcessor = new OrderProcessor();
        String result1 = longProcessor.processOrder(
            1002L, "Alice Johnson", "alice@example.com", "456 Oak Ave",
            java.util.Arrays.asList("Laptop", "Mouse"),
            java.util.Arrays.asList(999.99, 29.99),
            java.util.Arrays.asList(1, 2),
            "CREDIT_CARD", "9876543210987654", "11/24", "456"
        );
        System.out.println("Result: " + result1);
        
        // Refactored example
        System.out.println("\nRefactored (OrderProcessorRefactored.java):");
        OrderProcessorRefactored refactoredProcessor = new OrderProcessorRefactored();
        String result2 = refactoredProcessor.processOrder(order, paymentDetails);
        System.out.println("Result: " + result2);
        
        System.out.println("\nOrder Details:");
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Items: " + order.getTotalItems());
        System.out.println("Total: $" + String.format("%.2f", order.calculateTotal()));
        System.out.println("Status: " + order.getOrderStatus());
        
        System.out.println();
    }
}
