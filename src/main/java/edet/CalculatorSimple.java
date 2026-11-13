package edet;

public class CalculatorSimple {
    public static int sum(int a, int b){
        return a+b;
    }
    public static int sub(int a, int b){
        return a-b;
    }
    public static int multiply(int a, int b){
        return a*b;
    }
    public static double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return (double) a / b;
    }

    public static double sum(double a, double b){
        return a+b;
    }
    public static double sub(double a, double b){
        return a-b;
    }
    public static double multiply(double a, double b){
        return a*b;
    }
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return  a / b;
    }



}

