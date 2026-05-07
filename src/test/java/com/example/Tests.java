package com.example;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class Tests {
    
    private Customer testCustomer;
    private Item testItem1;
    private Item testItem2;
    private Order testOrder;
    private PaymentDetails testPaymentDetails;
    
    @Before
    public void setUp() {
        testCustomer = new Customer(1L, "John Doe", "john@example.com", "123 Main St, City, State");
        testItem1 = new Item(1L, "Laptop", "High-performance laptop", 999.99, 1, "Electronics", "LAPTOP-001");
        testItem2 = new Item(2L, "Mouse", "Wireless mouse", 29.99, 2, "Electronics", "MOUSE-002");
        testOrder = new Order(1001L, testCustomer);
        testOrder.addItem(testItem1);
        testOrder.addItem(testItem2);
        testPaymentDetails = new PaymentDetails("CREDIT_CARD", "1234567890123456", "12/25", "123");
    }
    
    // Calculator Tests
    @Test
    public void testCalculatorAdd() {
        assertEquals(5.0, Calculator.add(2.0, 3.0), 0.001);
        assertEquals(-1.0, Calculator.add(-2.0, 1.0), 0.001);
    }
    
    @Test
    public void testCalculatorSubtract() {
        assertEquals(2.0, Calculator.subtract(5.0, 3.0), 0.001);
        assertEquals(-3.0, Calculator.subtract(0.0, 3.0), 0.001);
    }
    
    @Test
    public void testCalculatorMultiply() {
        assertEquals(6.0, Calculator.multiply(2.0, 3.0), 0.001);
        assertEquals(0.0, Calculator.multiply(5.0, 0.0), 0.001);
    }
    
    @Test
    public void testCalculatorDivide() {
        assertEquals(2.0, Calculator.divide(6.0, 3.0), 0.001);
        assertEquals(-2.0, Calculator.divide(-6.0, 3.0), 0.001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculatorDivideByZero() {
        Calculator.divide(5.0, 0.0);
    }
    
    @Test
    public void testCalculatorPower() {
        assertEquals(8.0, Calculator.power(2.0, 3.0), 0.001);
        assertEquals(1.0, Calculator.power(5.0, 0.0), 0.001);
    }
    
    @Test
    public void testCalculatorSquareRoot() {
        assertEquals(4.0, Calculator.squareRoot(16.0), 0.001);
        assertEquals(0.0, Calculator.squareRoot(0.0), 0.001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculatorSquareRootNegative() {
        Calculator.squareRoot(-4.0);
    }
    
    // Bad Calculator Tests (Calc.java)
    @Test
    public void testCalcAdd() {
        assertEquals(5.0, Calc.x(2.0, 3.0), 0.001);
    }
    
    @Test
    public void testCalcSubtract() {
        assertEquals(2.0, Calc.y(5.0, 3.0), 0.001);
    }
    
    @Test
    public void testCalcMultiply() {
        assertEquals(6.0, Calc.z(2.0, 3.0), 0.001);
    }
    
    @Test
    public void testCalcDivide() {
        assertEquals(2.0, Calc.w(6.0, 3.0), 0.001);
    }
    
    // Customer Tests
    @Test
    public void testCustomerCreation() {
        assertEquals("John Doe", testCustomer.getName());
        assertEquals("john@example.com", testCustomer.getEmail());
        assertEquals("123 Main St, City, State", testCustomer.getShippingAddress());
    }
    
    @Test
    public void testCustomerSetters() {
        testCustomer.setName("Jane Smith");
        testCustomer.setEmail("jane@example.com");
        testCustomer.setPhone("555-1234");
        
        assertEquals("Jane Smith", testCustomer.getName());
        assertEquals("jane@example.com", testCustomer.getEmail());
        assertEquals("555-1234", testCustomer.getPhone());
    }
    
    // Item Tests
    @Test
    public void testItemCreation() {
        assertEquals("Laptop", testItem1.getName());
        assertEquals(999.99, testItem1.getPrice(), 0.001);
        assertEquals(1, testItem1.getQuantity());
    }
    
    @Test
    public void testItemTotalPrice() {
        assertEquals(999.99, testItem1.getTotalPrice(), 0.001);
        assertEquals(59.98, testItem2.getTotalPrice(), 0.001); // 29.99 * 2
    }
    
    @Test
    public void testItemQuantityOperations() {
        Item item = new Item(3L, "Keyboard", 49.99, 1);
        item.addQuantity(2);
        assertEquals(3, item.getQuantity());
        
        item.removeQuantity(1);
        assertEquals(2, item.getQuantity());
        
        assertTrue(item.isInStock());
    }
    
    // Order Tests
    @Test
    public void testOrderCreation() {
        assertEquals(Long.valueOf(1001L), testOrder.getId());
        assertEquals(testCustomer, testOrder.getCustomer());
        assertEquals(2, testOrder.getItems().size());
        assertEquals("PENDING", testOrder.getOrderStatus());
    }
    
    @Test
    public void testOrderTotalCalculation() {
        double expected = 999.99 + (29.99 * 2); // 1059.97
        assertEquals(expected, testOrder.calculateTotal(), 0.001);
    }
    
    @Test
    public void testOrderTotalItems() {
        assertEquals(3, testOrder.getTotalItems()); // 1 laptop + 2 mice
    }
    
    @Test
    public void testOrderStatusChanges() {
        testOrder.markAsShipped();
        assertEquals("SHIPPED", testOrder.getOrderStatus());
        assertNotNull(testOrder.getShippingDate());
        
        testOrder.markAsDelivered();
        assertEquals("DELIVERED", testOrder.getOrderStatus());
        
        testOrder.markAsCancelled();
        assertEquals("CANCELLED", testOrder.getOrderStatus());
    }
    
    @Test
    public void testOrderItemOperations() {
        Item newItem = new Item(3L, "Monitor", 199.99, 1);
        testOrder.addItem(newItem);
        assertEquals(3, testOrder.getItems().size());
        
        testOrder.removeItem(newItem);
        assertEquals(2, testOrder.getItems().size());
        
        testOrder.removeItem(1L);
        assertEquals(1, testOrder.getItems().size());
    }
    
    // PaymentDetails Tests
    @Test
    public void testPaymentDetailsCreation() {
        assertEquals("CREDIT_CARD", testPaymentDetails.getMethod());
        assertEquals("1234567890123456", testPaymentDetails.getCreditCardNumber());
        assertEquals("12/25", testPaymentDetails.getExpirationDate());
        assertEquals("123", testPaymentDetails.getCvv());
    }
    
    @Test
    public void testPayPalPaymentDetails() {
        PaymentDetails paypalPayment = new PaymentDetails("PAYPAL", "user@paypal.com");
        assertEquals("PAYPAL", paypalPayment.getMethod());
        assertEquals("user@paypal.com", paypalPayment.getPaypalEmail());
    }
    
    // OrderProcessor Tests (Long Method Version)
    @Test
    public void testOrderProcessorLongMethod() {
        OrderProcessor processor = new OrderProcessor();
        
        List<String> itemNames = Arrays.asList("Laptop", "Mouse");
        List<Double> itemPrices = Arrays.asList(999.99, 29.99);
        List<Integer> quantities = Arrays.asList(1, 2);
        
        String result = processor.processOrder(
            1002L, "Alice Johnson", "alice@example.com", "456 Oak Ave",
            itemNames, itemPrices, quantities,
            "CREDIT_CARD", "9876543210987654", "11/24", "456"
        );
        
        assertTrue(result.contains("Order processed successfully"));
        assertTrue(result.contains("1002"));
    }
    
    @Test
    public void testOrderProcessorLongMethodValidation() {
        OrderProcessor processor = new OrderProcessor();
        
        // Test invalid order ID
        String result = processor.processOrder(
            null, "Alice Johnson", "alice@example.com", "456 Oak Ave",
            Arrays.asList("Laptop"), Arrays.asList(999.99), Arrays.asList(1),
            "CREDIT_CARD", "9876543210987654", "11/24", "456"
        );
        assertTrue(result.contains("Invalid order ID"));
        
        // Test invalid email
        result = processor.processOrder(
            1002L, "Alice Johnson", "invalid-email", "456 Oak Ave",
            Arrays.asList("Laptop"), Arrays.asList(999.99), Arrays.asList(1),
            "CREDIT_CARD", "9876543210987654", "11/24", "456"
        );
        assertTrue(result.contains("Invalid customer email"));
    }
    
    // OrderProcessorRefactored Tests
    @Test
    public void testOrderProcessorRefactored() {
        OrderProcessorRefactored processor = new OrderProcessorRefactored();
        
        Order order = new Order(1003L, testCustomer);
        order.addItem(testItem1);
        order.setPaymentDetails(testPaymentDetails);
        
        String result = processor.processOrder(order, testPaymentDetails);
        
        assertTrue(result.contains("Order processed successfully"));
        assertTrue(result.contains("1003"));
    }
    
    @Test
    public void testOrderProcessorRefactoredValidation() {
        OrderProcessorRefactored processor = new OrderProcessorRefactored();
        
        // Test null order
        String result = processor.processOrder(null, testPaymentDetails);
        assertTrue(result.contains("Order processing failed"));
        assertTrue(result.contains("Order cannot be null"));
        
        // Test invalid order
        Order invalidOrder = new Order(null, null);
        result = processor.processOrder(invalidOrder, testPaymentDetails);
        assertTrue(result.contains("Order processing failed"));
    }
    
    // Integration Test
    @Test
    public void testCompleteOrderProcessing() {
        // Create a complete order scenario
        Customer customer = new Customer(2L, "Bob Wilson", "bob@example.com", "789 Pine St");
        Item item1 = new Item(4L, "Phone", 699.99, 1);
        Item item2 = new Item(5L, "Case", 19.99, 1);
        
        Order order = new Order(2001L, customer);
        order.addItem(item1);
        order.addItem(item2);
        
        PaymentDetails payment = new PaymentDetails("CREDIT_CARD", "1111222233334444", "08/26", "789");
        order.setPaymentDetails(payment);
        
        // Process with refactored processor
        OrderProcessorRefactored processor = new OrderProcessorRefactored();
        String result = processor.processOrder(order, payment);
        
        assertTrue(result.contains("Order processed successfully"));
        assertEquals(719.98, order.calculateTotal(), 0.001); // 699.99 + 19.99
        assertEquals(2, order.getTotalItems());
    }
}
