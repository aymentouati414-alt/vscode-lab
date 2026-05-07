package com.example;

// BAD NAMING - This class demonstrates poor naming conventions
public class Calc {
    
    // Method with unclear name
    public static double x(double a, double b) {
        return a + b;
    }
    
    // Another poorly named method
    public static double y(double a, double b) {
        return a - b;
    }
    
    // Unclear variable names
    public static double z(double p, double q) {
        double r = p * q;
        return r;
    }
    
    // More bad naming
    public static double w(double u, double v) {
        if (v == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return u / v;
    }
}
