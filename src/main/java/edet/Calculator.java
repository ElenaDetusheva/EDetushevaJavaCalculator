package edet;

public class Calculator {

    private static final double PRECISION_SCALE = 1e10;
    private static final double MAX_SAFE_INTEGER = 9_007_199_254_740_991.0; // 2^53-1

    public static Number sum(double a, double b) {
        return formatResult(a + b);
    }

    public static Number sub(double a, double b) {
        return formatResult(a - b);
    }

    public static Number multiply(double a, double b) {
        return formatResult(a * b);
    }

    public static Number divide(double a, double b) {
        if (b == 0.0) {
            throw new ArithmeticException("Деление на ноль запрещено");
        }
        return formatResult(a / b);
    }

    private static Number formatResult(double result) {

        if (Double.isNaN(result)) {
            throw new IllegalArgumentException("Математически неопределенный результат (NaN)");
        }
        if (Math.abs(result) > MAX_SAFE_INTEGER) {
            throw new ArithmeticException("Результат выходит за пределы точных вычислений");
        }

        double cleaned = result;
        if (Math.abs(result) < MAX_SAFE_INTEGER / PRECISION_SCALE) {
            cleaned = Math.round(result * PRECISION_SCALE) / PRECISION_SCALE;
        }

        if (cleaned % 1 == 0) {
            return (long) cleaned;
        }

        return cleaned;
    }
}
