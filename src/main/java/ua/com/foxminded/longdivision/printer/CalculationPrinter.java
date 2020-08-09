package ua.com.foxminded.longdivision.printer;

import ua.com.foxminded.longdivision.formatter.Formatter;
import ua.com.foxminded.longdivision.model.LongDivisionResult;
import ua.com.foxminded.longdivision.service.Calculator;

public class CalculationPrinter {

    private Calculator<LongDivisionResult> calculator;
    private Formatter formatter;

    public CalculationPrinter(Calculator<LongDivisionResult> calculator, Formatter formatter) {
        this.calculator = calculator;
        this.formatter = formatter;
    }

    public void print(int dividend, int divisor) {
        LongDivisionResult result = calculator.calculate(dividend, divisor);
        System.out.println(formatter.format(result));
    }
}
