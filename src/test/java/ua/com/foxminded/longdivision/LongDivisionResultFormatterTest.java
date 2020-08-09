package ua.com.foxminded.longdivision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ua.com.foxminded.longdivision.formatter.Formatter;
import ua.com.foxminded.longdivision.formatter.LongDivisionResultFormatter;
import ua.com.foxminded.longdivision.model.LongDivisionResult;
import ua.com.foxminded.longdivision.service.Calculator;
import ua.com.foxminded.longdivision.service.LongDivisionCalculator;

class LongDivisionResultFormatterTest {

    private static final String NL = System.lineSeparator();
    private Formatter formatter = new LongDivisionResultFormatter();
    private Calculator<LongDivisionResult> calculator = new LongDivisionCalculator();

    @Test
    void shouldReturnProperCalculationsIfDividendIsZero() {
        int dividend = 0;
        int divisor = 1;

        String expected = "_0|1" + NL +
                        " 0|-" + NL +
                        " -|0" + NL +
                        " 0" + NL;

        String actual = formatter.format(calculator.calculate(dividend, divisor));

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnProperCalculationsIfDivisorIsOne() {
        int dividend = 10000;
        int divisor = 1;

        String expected = "_10000|1" + NL +
                        " 1    |-----" + NL +
                        " -    |10000" + NL +
                        " 0" + NL;

        String actual = formatter.format(calculator.calculate(dividend, divisor));

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnProperCalculationsIfDividendEqualsDivisor() {
        int dividend = 10000;
        int divisor = 10000;

        String expected = "_10000|10000" + NL +
                        " 10000|-" + NL +
                        " -----|1" + NL +
                        "     0" + NL;

        String actual = formatter.format(calculator.calculate(dividend, divisor));

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnProperCalculationsIfDividendLessThanDivisor() {
        int dividend = 5000;
        int divisor = 10000;

        String expected = "_5000|10000" + NL +
                        " 0   |-" + NL +
                        " -   |0" + NL +
                        " 5000"  + NL;

        String actual = formatter.format(calculator.calculate(dividend, divisor));

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnProperCalculationsIfDividendContainsZeroes() {
        int dividend = 10000;
        int divisor = 5;

        String expected = "_10000|5" + NL +
                        " 10   |----" + NL +
                        " --   |2000" + NL +
                        "  0" + NL;

        String actual = formatter.format(calculator.calculate(dividend, divisor));

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnProperCalculationsWhenMultiFigureDivisorSupplied() {
        int dividend = 231245125;
        int divisor = 144;

        String expected = "_231245125|144" + NL +
                        " 144      |-------" + NL +
                        " ---      |1605868" + NL +
                        " _872" + NL +
                        "  864" + NL +
                        "  ---" + NL +
                        "   _845" + NL +
                        "    720" + NL +
                        "    ---" + NL +
                        "   _1251" + NL +
                        "    1152" + NL +
                        "    ----" + NL +
                        "     _992" + NL +
                        "      864" + NL +
                        "      ---" + NL +
                        "     _1285" + NL +
                        "      1152" + NL +
                        "      ----" + NL +
                        "       133"  + NL;

        String actual = formatter.format(calculator.calculate(dividend, divisor));

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnProperCalculationsWhenSingleFigureDivisorSupplied() {
        int dividend = 78945;
        int divisor = 4;

        String expected = "_78945|4" + NL +
                        " 4    |-----" + NL +
                        " -    |19736" + NL +
                        "_38" + NL +
                        " 36" + NL +
                        " --" + NL +
                        " _29" + NL +
                        "  28" + NL +
                        "  --" + NL +
                        "  _14" + NL +
                        "   12" + NL +
                        "   --" + NL +
                        "   _25" + NL +
                        "    24" + NL +
                        "    --" + NL +
                        "     1" + NL;

        String actual = formatter.format(calculator.calculate(dividend, divisor));

        assertEquals(expected, actual);
    }
}
