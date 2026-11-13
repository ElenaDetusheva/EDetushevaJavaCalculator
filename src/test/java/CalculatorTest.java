import edet.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class CalculatorTest {

    private static final double MAX_SAFE_INTEGER = 9_007_199_254_740_992.0; // 2^53
    // 1ю\. Данные для сложения . Позитивные тесты
    @DataProvider(name = "sumData")
    public Object[][] provideSumData() {
        return new Object[][] {
                { 1, 9, 10L },
                { 1.5, 9.1, 10.6 },
                { -5, -3, -8L },
                { 0, 0, 0L },
                { Integer.MAX_VALUE, 1, 2147483648L }, // Проверка выхода за пределы int в long
                { Integer.MAX_VALUE, Integer.MIN_VALUE, -1L }
        };
    }

    @Test(dataProvider = "sumData")
    public void testSum(double a, double b, Number expected) {
        Assert.assertEquals(Calculator.sum(a, b), expected);
    }

    // 2. Данные для вычитания (метод sub).Позитивные тесты
    @DataProvider(name = "subData")
    public Object[][] provideSubData() {
        return new Object[][] {
                { 10, 4, 6L },
                { 5.5, 2, 3.5 },
                { 3, 10, -7L },
                { -5, -5, 0L },
            { Integer.MIN_VALUE, 1, -2147483649L } // Выход за минимальный int в long
        };
    }

    @Test(dataProvider = "subData")
    public void testSub(double a, double b, Number expected) {

        Assert.assertEquals(Calculator.sub(a, b), expected);
    }

    // 3. Данные для умножения Позитивные тесты
    @DataProvider(name = "multiplyData")
    public Object[][] provideMultiplyData() {
        return new Object[][] {
                { 3, 4, 12L },
                { 2.5, 2, 5L },          // Результат 5.0 без дробной части -> Long
                { 2.5, 3, 7.5 },
                { -5, 0, 0L },           // Проверка защиты от -0.0 -> 0L
                { -4, -3, 12L },
                { Integer.MAX_VALUE, 2, 4294967294L }
        };
    }

    @Test(dataProvider = "multiplyData")
    public void testMultiply(double a, double b, Number expected) {
        Assert.assertEquals(Calculator.multiply(a, b), expected);
    }

    // 4. Данные для деления. Позитивные тесты
    @DataProvider(name = "divideData")
    public Object[][] provideDivideData() {
        return new Object[][] {
                { 10, 2, 5L },
                { 5, 2, 2.5 },
                { 7.5, 2.5, 3L },
                { 5, 0.5, 10L },
                { 0, -5, 0L },           // Ноль при делении на отрицательное число -> 0L
                { -10, -2, 5L },
                { -5, 2, -2.5 },
                { Integer.MIN_VALUE, -1, 2147483648L }
        };
    }

    @Test(dataProvider = "divideData")
    public void testDivide(double a, double b, Number expected) {
        Assert.assertEquals(Calculator.divide(a, b), expected);
    }

    // 5. Проверка исключения при делении на ноль (и на -0.0)
    @DataProvider(name = "zeroDivisors")
    public Object[][] provideZeroDivisors() {
        return new Object[][] {
                { 10, 0 },
                { -5.5, -0.0 },
                { 0, 0 }
        };
    }

    @Test(dataProvider = "zeroDivisors")
    public void testDivideByZero(double a, double b) {
        Assert.assertThrows(ArithmeticException.class, () -> Calculator.divide(a, b));
    }

    //6. Негативные тесты NaN в параметрах

    @Test
    public void testNaNInDevision() {
        ArithmeticException exception = Assert.expectThrows(
                ArithmeticException.class,
                () -> Calculator.divide(5, Math.sqrt(-4))
        );
        Assert.assertEquals(exception.getMessage(), "Математически неопределенный результат (NaN)");

    }

    @Test
    public void testNaNInSum() {
        ArithmeticException exception = Assert.expectThrows(
                ArithmeticException.class, () -> Calculator.sum(5, Math.sqrt(-4))
        );

        Assert.assertEquals( exception.getMessage(), "Математически неопределенный результат (NaN)" );
    }

    @Test
    public void testNaNInSub() {
        ArithmeticException exception = Assert.expectThrows(
                ArithmeticException.class, () -> Calculator.sub(5, Math.sqrt(-4)));
        Assert.assertEquals( exception.getMessage(), "Математически неопределенный результат (NaN)" );
    }

    @Test
    public void testNaNInMultiply() {
        ArithmeticException exception = Assert.expectThrows(
                ArithmeticException.class, () -> Calculator.multiply(5, Math.sqrt(-4)));
        Assert.assertEquals( exception.getMessage(), "Математически неопределенный результат (NaN)" );
    }

    // 7. Негативный . Выход за допустимые пределы

    @DataProvider(name = "negativeSumData")
    public Object[][] negativeSumData() {
        return new Object[][] {
                { MAX_SAFE_INTEGER -1, 1},
                {0, MAX_SAFE_INTEGER  },
                {0, -MAX_SAFE_INTEGER  },
                {1, MAX_SAFE_INTEGER  },
                {10, MAX_SAFE_INTEGER  },
                { MAX_SAFE_INTEGER-100,MAX_SAFE_INTEGER-1000}
        };
    }

    @Test(dataProvider = "negativeSumData")
    public void testSumOver(double a, double b) {

        ArithmeticException exception = Assert.expectThrows(
                ArithmeticException.class, () -> Calculator.sum(a, b)
        );

        Assert.assertEquals( exception.getMessage(), "Результат выходит за пределы точных вычислений" );
    }


    @DataProvider(name = "negativeSubData")
    public Object[][] negativeSubData() {
        return new Object[][]{
                {0, MAX_SAFE_INTEGER  },
                {0, -MAX_SAFE_INTEGER },
                {-MAX_SAFE_INTEGER, MAX_SAFE_INTEGER },
                {MAX_SAFE_INTEGER ,0 },
                { -MAX_SAFE_INTEGER,0 }

        };
    }

    @Test(dataProvider = "negativeSubData")
    public void testSubOver(double a, double b) {

        ArithmeticException exception = Assert.expectThrows(
                ArithmeticException.class, () -> Calculator.sub(a, b)
        );

        Assert.assertEquals( exception.getMessage(), "Результат выходит за пределы точных вычислений" );
    }

    @DataProvider(name = "negativeMultiData")
    public Object[][] negativeMultiData() {
        return new Object[][]{
                {1.1, MAX_SAFE_INTEGER  },
                {2, -MAX_SAFE_INTEGER },
                {-MAX_SAFE_INTEGER, MAX_SAFE_INTEGER },
                {MAX_SAFE_INTEGER ,-2}

        };
    }

    @Test(dataProvider = "negativeMultiData")
    public void testMultiOver(double a, double b) {

        ArithmeticException exception = Assert.expectThrows(
                ArithmeticException.class, () -> Calculator.multiply(a, b)
        );

        Assert.assertEquals( exception.getMessage(), "Результат выходит за пределы точных вычислений" );
    }
}
