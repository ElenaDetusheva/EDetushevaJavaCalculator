package edet;

import java.util.function.Supplier;



public class Main {
    public static void main(String[] args) {

       final double MAX_SAFE_INTEGER = 9_007_199_254_740_991.0;

        printSafe(() -> Calculator.sum(0.2, 0.1));
        printSafe(() -> Calculator.sum(-2, 3));

        printSafe(() -> Calculator.divide(15, 3));
        printSafe(() -> Calculator.multiply(5, 3));
        printSafe(() -> Calculator.sub(5, 3));

        printSafe(() -> Calculator.sum(5.2, 5));
        printSafe(() -> Calculator.divide(5.4, 3));
        printSafe(() -> Calculator.multiply(5, 3));
        printSafe(() -> Calculator.sub(5.423, 3));
        printSafe(() -> Calculator.divide(0.33, 3.3));
        printSafe(() -> Calculator.sum(0, 5));
        printSafe(() -> Calculator.divide(33, 3.3));
        printSafe(() -> Calculator.multiply(-5.0, 0));
        printSafe(() -> Calculator.sum(0.1, 0.2));
        printSafe(() -> Calculator.divide(0.1, 0.01));
        printSafe(() -> Calculator.multiply(0.1, 10));

        // Эти строки выбросят ошибку, но программа не упадет
        printSafe(() -> Calculator.sum(Long.MAX_VALUE, 5));
        printSafe(() -> Calculator.sub(Long.MAX_VALUE, 5));
        printSafe(() -> Calculator.divide(0, Math.sqrt(-4)));
        printSafe(() -> Calculator.sum(MAX_SAFE_INTEGER, 1));
    }

    private static void printSafe(Supplier<Number> operation) {
        try {
            System.out.println(operation.get());
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
      }
    }
}
