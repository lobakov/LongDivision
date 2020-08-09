package ua.com.foxminded.longdivision.formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import ua.com.foxminded.longdivision.model.LongDivisionResult;

public class LongDivisionResultFormatter implements Formatter {

    private static final String SPACE = " ";
    private static final String MINUS = "_";
    private static final String DELIMITER = "|";
    private static final String LINE = "-";
    private static final String EMPTY = "";
    private static final String NL = System.lineSeparator();

    @Override
    public String format(LongDivisionResult result) {
        List<String> preparedOutput = prepareOutput(result);
        StringJoiner joiner = new StringJoiner(NL);

        for (String line: preparedOutput) {
            joiner.add(line);
        }
        return joiner.toString();
    }

    private List<String> prepareOutput(LongDivisionResult result) {
        List<Integer> dividends = result.getDividends();
        List<Integer> divisors = result.getDivisors();
        List<Integer> remainders = result.getRemainders();
        int quotient = result.getQuotient();
        return convertDataToLines(dividends, divisors, remainders, quotient);
    }

    private List<String> convertDataToLines(List<Integer> dividends, List<Integer> divisors,
            List<Integer> remainders, Integer quotient) {
        List<String> lines = new ArrayList<>();
        lines.addAll(addTableLines(dividends, divisors, quotient));
        lines.addAll(addCalculationLines(dividends, divisors, remainders));
        lines.add(EMPTY);
        return lines;
    }

    private List<String> addTableLines(List<Integer> dividends, List<Integer> divisors, Integer quotient) {
        int dividend = dividends.get(0);
        int divisor = divisors.get(0);
        int firstDividend = dividends.get(1);
        int firstDivisor = divisors.get(1);
        int firstDividendLength = String.valueOf(firstDividend).length();
        int firstDivisorLength = String.valueOf(firstDivisor).length();
        int firstRemainderLength = 0;

        if (dividend >= divisor) {
            firstRemainderLength = firstDividendLength - firstDivisorLength;
        }
        int dividendLength = String.valueOf(dividend).length();
        int tableSpaces = dividendLength - firstDivisorLength;
        int offset = MINUS.length() + firstRemainderLength;

        List<String> lines = new ArrayList<>();
        lines.add(MINUS + dividend + DELIMITER + divisor);
        lines.add(SPACE.repeat(offset) + firstDivisor + SPACE.repeat(tableSpaces) + DELIMITER
                + LINE.repeat(quotient.toString().length()));
        lines.add(SPACE + LINE.repeat(firstDivisorLength) + SPACE.repeat(tableSpaces) + DELIMITER + quotient);
        return lines;
    }

    private List<String> addCalculationLines(List<Integer> dividends, List<Integer> divisors,
            List<Integer> remainders) {

        List<String> lines = new ArrayList<>();
        int dividendOffset = calculateDividendOffset(dividends.get(1), remainders.get(1));

        for (int i = 2; i < remainders.size(); i++) {
            Integer dividend = dividends.get(i);
            Integer divisor = divisors.get(i);
            lines.add(addDividendLine(dividend, dividendOffset));

            int divisorOffset = calculateSubDivisorOffset(dividend, divisor, dividendOffset);
            lines.add(addDivisorLine(divisor, divisorOffset));
            lines.add(addSeparatorLine(dividend, dividendOffset));

            int remainderOffset = calculateRemainderOffset(remainders.get(i));
            dividendOffset += dividend.toString().length() - remainderOffset;
        }
        lines.add(addCalculationRemainder(remainders, dividendOffset));
        return lines;
    }

    private String addDividendLine(int dividend, int dividendOffset) {
        return SPACE.repeat(dividendOffset) + MINUS + dividend;
    }

    private String addDivisorLine(Integer divisor, int divisorOffset) {
        return SPACE.repeat(divisorOffset) + divisor.toString();
    }

    private String addSeparatorLine(Integer dividend, int dividendOffset) {
        return SPACE.repeat(MINUS.length() + dividendOffset) + LINE.repeat(dividend.toString().length());
    }

    private int calculateSubDivisorOffset(Integer dividend, Integer divisor, int dividendOffset) {
        return MINUS.length() + dividendOffset + (dividend.toString().length() - divisor.toString().length());
    }

    private int calculateDividendOffset(Integer dividend, Integer remainder) {
        int remainderOffset = (remainder == 0) ? 0 : remainder.toString().length();
        return dividend.toString().length() - remainderOffset;
    }

    private int calculateRemainderOffset(Integer remainder) {
        return (remainder == 0) ? 0 : remainder.toString().length();
    }

    private String addCalculationRemainder(List<Integer> remainders, int offset) {
        Integer remainder = remainders.get(remainders.size() - 1);
        int remainderOffset = (remainder == 0) ? offset : MINUS.length() + offset;
        return SPACE.repeat(remainderOffset) + remainder.toString();
    }
}
