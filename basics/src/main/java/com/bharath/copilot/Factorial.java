package com.bharath.copilot;

//class that calculates the factorial of a number
public class Factorial {

    //method that calculates the factorial of a number
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } 
         return n * factorial(n - 1);
    }

    //main method
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }
}

