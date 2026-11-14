package edet;

public class Main {
    public static void main(String[] args) {

       final double MAX_SAFE_INTEGER = 9_007_199_254_740_991.0;

        System.out.println( "0.2 + 0.1 = " + Calculator.sum(0.2, 0.1));
        System.out.println( "-2 + 3 = " + Calculator.sum(-2, 3));
        System.out.println( "15 / 3 = " + Calculator.divide(15, 3));
        System.out.println( "5 * 3 = " + Calculator.multiply(5, 3));
        System.out.println( "5 - 3 = " + Calculator.sub(5, 3));
        System.out.println( "5.2 + 3 = " + Calculator.sum(5.2, 5));
        System.out.println( "5.4 / 3 = " + Calculator.divide(5.4, 3));
        System.out.println( "5.423 - 3 = " + Calculator.sub(5.423, 3));
        System.out.println( "0.33 / 3.3 = " + Calculator.divide(0.33, 3.3));
        System.out.println( "0 + 5 = " + Calculator.sum(0, 5));
        System.out.println( "33 / 3.3 = " + Calculator.divide(33, 3.3));
        System.out.println("-5 / 0 = " +  Calculator.multiply(-5.0, 0));
        System.out.println( "0.1 + 0.2 = " + Calculator.sum(0.1, 0.2));
        System.out.println("0.1/ 0.01 = " +  Calculator.divide(0.1, 0.01));
        System.out.println( "0.1 * 10 = " + Calculator.multiply(0.1, 10));

        System.out.println( "===================================================== " );
        System.out.println( "Обработка исключений");
        System.out.print("0 / Math.sqrt(-4) = ");
        try {
            System.out.println(Calculator.divide(0, Math.sqrt(-4)));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        System.out.print("MAX_SAFE_INTEGER + 1 = ");
        try {
            System.out.println(Calculator.sum(MAX_SAFE_INTEGER, 1));
        } catch (ArithmeticException e) {
            System.out.println( e.getMessage());
        }

        System.out.print("15 / 0 = ");
        try {
            System.out.println(Calculator.divide(15, 0));
        } catch (ArithmeticException e) {
            System.out.println( e.getMessage());
        }

        System.out.print("0 / 15 = ");
        try {
            System.out.println(Calculator.divide(0, 5));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        System.out.println( "===================================================== " );
        System.out.println( "Несовершенство моего калькулятора");
        System.out.println( "900720.1 + 0.2 = " + Calculator.sum(900720.1, 0.2));
        System.out.println( "1 / 100_000_000_000.0 = " + Calculator.divide(1, 100_000_000_000.0));
    }
}
