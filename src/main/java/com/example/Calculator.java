package com.example;

// REFACTORED - This class demonstrates good naming conventions
public class Calculator {
    
    /**
     * Adds two numbers together
     * @param firstNumber the first operand
     * @param secondNumber the second operand
     * @return the sum of the two numbers
     */
    public static double add(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }
    
    /**
     * Subtracts the second number from the first
     * @param minuend the number to be subtracted from
     * @param subtrahend the number to subtract
     * @return the difference between the two numbers
     */
    public static double subtract(double minuend, double subtrahend) {
        return minuend - subtrahend;
    }
    
    /**
     * Multiplies two numbers together
     * @param firstFactor the first factor
     * @param secondFactor the second factor
     * @return the product of the two numbers
     */
    public static double multiply(double firstFactor, double secondFactor) {
        double product = firstFactor * secondFactor;
        return product;
    }
    
    /**
     * Divides the first number by the second
     * @param dividend the number to be divided
     * @param divisor the number to divide by
     * @return the quotient of the division
     * @throws IllegalArgumentException if divisor is zero
     */
    public static double divide(double dividend, double divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return dividend / divisor;
    }
    
    /**
     * Calculates the power of a number
     * @param base the base number
     * @param exponent the exponent
     * @return base raised to the power of exponent
     */
    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
    
    /**
     * Calculates the square root of a number
     * @param number the number to calculate square root for
     * @return the square root of the number
     * @throws IllegalArgumentException if number is negative
     */
    public static double squareRoot(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(number);
    }
}
