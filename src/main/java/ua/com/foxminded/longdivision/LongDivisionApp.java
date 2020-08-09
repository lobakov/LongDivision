package ua.com.foxminded.longdivision;

import ua.com.foxminded.longdivision.formatter.LongDivisionResultFormatter;
import ua.com.foxminded.longdivision.printer.CalculationPrinter;
import ua.com.foxminded.longdivision.service.LongDivisionCalculator;

public class LongDivisionApp {

    public static void main(String[] args) {
        int dividend = 78945;
        int divisor = 4;

        CalculationPrinter printer = new CalculationPrinter(new LongDivisionCalculator(),
                new LongDivisionResultFormatter());
        printer.print(dividend, divisor);
    }
}
