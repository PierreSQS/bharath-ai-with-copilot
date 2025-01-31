package com.bharath.copilot;

import java.math.BigInteger;

// class to calculate factorial of a number
public class Factorial {
    public static void main(String[] args) {
        int n = 5;
        System.out.println("Factorial of " + n + " is " + factorial(n));
    }

    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
