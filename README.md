# Java Project Example

A complete Java project demonstrating good and bad coding practices, including:

- **Bad naming vs Good naming** (Calc.java vs Calculator.java)
- **Long method vs Refactored method** (OrderProcessor.java vs OrderProcessorRefactored.java)
- **Model classes** (Customer.java, Item.java, Order.java, PaymentDetails.java)
- **Comprehensive unit tests** (Tests.java)
- **Gradle build configuration** (build.gradle)

## Project Structure

```
vscode-lab/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── example/
│   │               ├── Main.java                    # Main demonstration class
│   │               ├── Calc.java                    # Bad naming example
│   │               ├── Calculator.java              # Good naming example (refactored)
│   │               ├── OrderProcessor.java         # Long method example
│   │               ├── OrderProcessorRefactored.java # Refactored version
│   │               ├── Customer.java                # Customer model
│   │               ├── Item.java                    # Item model
│   │               ├── Order.java                   # Order model
│   │               └── PaymentDetails.java          # Payment details model
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── Tests.java                   # Comprehensive unit tests
├── build.gradle                                      # Gradle build configuration
└── README.md                                         # This file
```

## Features Demonstrated

### 1. Naming Conventions
- **Calc.java**: Shows poor naming with methods like `x()`, `y()`, `z()`, `w()`
- **Calculator.java**: Demonstrates good naming with descriptive methods like `add()`, `subtract()`, `multiply()`, `divide()`

### 2. Method Extraction
- **OrderProcessor.java**: Contains a long `processOrder()` method that does too many things
- **OrderProcessorRefactored.java**: Shows the same functionality broken down into smaller, focused methods

### 3. Object-Oriented Design
- **Customer.java**: Represents a customer with proper encapsulation
- **Item.java**: Represents an item with business logic methods
- **Order.java**: Represents an order with relationships to Customer and Items
- **PaymentDetails.java**: Encapsulates payment information

### 4. Testing
- **Tests.java**: Comprehensive unit tests covering all classes and scenarios
- Tests for both bad and good examples
- Integration tests demonstrating complete workflows

## How to Run

### Prerequisites
- Java 11 or higher
- Gradle 6.0 or higher

### Commands

```bash
# Build the project
gradle build

# Run the main demonstration
gradle run

# Run tests
gradle test

# Run specific examples
gradle runBadNamingExample
gradle runGoodNamingExample
gradle runOrderProcessorExample

# Clean build
gradle clean
```

### Running from IDE
1. Open the project in your favorite IDE (IntelliJ, Eclipse, etc.)
2. Run the `Main.java` class to see all demonstrations
3. Run `Tests.java` to execute all unit tests

## What You'll Learn

1. **Importance of Good Naming**: Compare `Calc.x(a, b)` vs `Calculator.add(firstNumber, secondNumber)`

2. **Method Extraction Benefits**: See how a 100+ line method becomes maintainable with smaller, focused methods

3. **Object-Oriented Principles**: Proper encapsulation, relationships, and business logic in model classes

4. **Testing Best Practices**: Comprehensive test coverage including edge cases and validation

5. **Build Automation**: Gradle configuration for Java projects with testing and custom tasks

## Code Quality Examples

### Before (Bad Naming)
```java
public static double x(double a, double b) {
    return a + b;
}
```

### After (Good Naming)
```java
public static double add(double firstNumber, double secondNumber) {
    return firstNumber + secondNumber;
}
```

### Before (Long Method)
```java
public String processOrder(...) {
    // 100+ lines doing validation, calculation, payment, email, inventory...
}
```

### After (Refactored)
```java
public String processOrder(Order order, PaymentDetails paymentDetails) {
    validateOrderInput(order, paymentDetails);
    double total = calculateOrderTotal(order);
    double discount = applyDiscountIfEligible(total);
    // ... each step in its own method
}
```

## Extending the Project

- Add more payment methods (Bitcoin, Apple Pay, etc.)
- Implement discount strategies
- Add inventory management system
- Create REST API endpoints
- Add database persistence
- Implement logging and monitoring

This project serves as a comprehensive example of Java best practices and refactoring techniques.

## Related Labs
- [Lab 01: Python Development with VS Code](docs/lab01-python-test.md)
- [Lab 02: Building Java Applications Using Gradle](docs/gradle-lan.md)
- [Lab 03: Refactoring Java code using VS Code](docs/refactoring-lab.md)
