package edet;

public class CalculatorHighLevel {

    // Множитель для очистки двоичной погрешности (10 знаков после запятой)
    private static final double PRECISION_SCALE = 1e10;

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
        if (Double.isInfinite(result) || Double.isNaN(result)) {
            throw new ArithmeticException("Результат выходит за допустимые пределы типа double");
        }

        // Округляем до 10 знаков, только если число не слишком большое (чтобы не переполнить Math.round)
        double cleaned = result;
        if (Math.abs(result) < Long.MAX_VALUE / PRECISION_SCALE) {
            cleaned = Math.round(result * PRECISION_SCALE) / PRECISION_SCALE;
        }

        // Проверяем, что число целое и безопасно помещается в long
        if (cleaned % 1 == 0 && cleaned >= Long.MIN_VALUE && cleaned <= Long.MAX_VALUE) {
            return (long) cleaned;
        }

        return cleaned;
    }
//    public static Number sum(double a, double b){
//        double result = a+b;
//
////        if (Double.isInfinite(result)) {
////            throw new ArithmeticException("Произошло переполнение типа double");
////        }
//        return (result % 1 == 0) ? (Number) (long) result : result;
//
//
//    }
//
//    public static Number sub(double a, double b){
//        double result = a-b;
//        if (Double.isInfinite(result)) {
//            throw new ArithmeticException("Произошло переполнение типа double");
//        }
//        return (result % 1 == 0) ? (Number) (long) result : result;
//
//    }
//
//    public static Number multiply(double a, double b){
//        double result = a * b;
//            if (Double.isInfinite(result)) {
//                throw new ArithmeticException("Произошло переполнение типа double");
//            }
//        return (result % 1 == 0) ? (Number) (long) result : result;
//    }
//
//    public static Number divide(double a, double b) {
//        if (b == 0) {
//            throw new ArithmeticException("Деление на ноль невозможно");
//        }
//
//        double result = a / b;
//        if (result % 1 == 0) {
//            return (long) result;
//        }
//        return result;
//    }
//
//    private double validateResult(double result) {
//        if (Double.isInfinite(result) || Double.isNaN(result)) {
//            throw new ArithmeticException("Результат выходит за допустимые пределы типа double");
//        }
//        return result;
//    }
}
