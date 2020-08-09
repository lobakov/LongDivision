package ua.com.foxminded.longdivision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ua.com.foxminded.longdivision.model.LongDivisionResult;
import ua.com.foxminded.longdivision.service.Calculator;
import ua.com.foxminded.longdivision.service.LongDivisionCalculator;

class LongDivisionCalculatorTest {

    private Calculator<LongDivisionResult> calculator = new LongDivisionCalculator();

    @Test
    void shouldReturnProperCalculationsIfDividendIsZero() {
        int dividend = 0;
        int divisor = 1;

        LongDivisionResult expected = new LongDivisionResult();
        expected.addData(dividend, divisor, 0);
        expected.addData(0, 0, 0);
        expected.addQuotient(0);

        LongDivisionResult actual = calculator.calculate(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnProperCalculationsIfDivisorIsOne() {
        int dividend = 10000;
        int divisor = 1;

        LongDivisionResult expected = new LongDivisionResult();
        expected.addData(dividend, divisor, 0);
        expected.addData(1, 1, 0);
        expected.addQuotient(10000);

        LongDivisionResult actual = calculator.calculate(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnProperCalculationsIfDividendEqualsDivisor() {
        int dividend = 10000;
        int divisor = 10000;

        LongDivisionResult expected = new LongDivisionResult();
        expected.addData(dividend, divisor, 0);
        expected.addData(10000, 10000, 0);
        expected.addQuotient(1);

        LongDivisionResult actual = calculator.calculate(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnProperCalculationsIfDividendLessThanDivisor() {
        int dividend = 5000;
        int divisor = 10000;

        LongDivisionResult expected = new LongDivisionResult();
        expected.addData(dividend, divisor, 5000);
        expected.addData(5000, 0, 5000);
        expected.addQuotient(0);

        LongDivisionResult actual = calculator.calculate(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnProperCalculationsIfDividendContainsZeroes() {
        int dividend = 10000;
        int divisor = 5;

        LongDivisionResult expected = new LongDivisionResult();
        expected.addData(dividend, divisor, 0);
        expected.addData(10, 10, 0);
        expected.addQuotient(2000);

        LongDivisionResult actual = calculator.calculate(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnProperCalculationsWhenMultiFigureDivisorSupplied() {
        int dividend = 231245125;
        int divisor = 144;

        LongDivisionResult expected = new LongDivisionResult();
        expected.addData(dividend, divisor, 133);
        expected.addData(231, 144, 87);
        expected.addData(872, 864, 8);
        expected.addData(845, 720, 125);
        expected.addData(1251, 1152, 99);
        expected.addData(992, 864, 128);
        expected.addData(1285, 1152, 133);
        expected.addQuotient(1605868);

        LongDivisionResult actual = calculator.calculate(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnProperCalculationsWhenSingleFigureDivisorSupplied() {
        int dividend = 78945;
        int divisor = 4;

        LongDivisionResult expected = new LongDivisionResult();
        expected.addData(dividend, divisor, 1);
        expected.addData(7, 4, 3);
        expected.addData(38, 36, 2);
        expected.addData(29, 28, 1);
        expected.addData(14, 12, 2);
        expected.addData(25, 24, 1);
        expected.addQuotient(19736);

        LongDivisionResult actual = calculator.calculate(dividend, divisor);

        assertEquals(expected, actual);
    }
}
