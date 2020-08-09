package ua.com.foxminded.longdivision.service;

import java.util.StringJoiner;
import ua.com.foxminded.longdivision.model.LongDivisionResult;

public class LongDivisionCalculator implements Calculator<LongDivisionResult> {

    private static final String ZERO = "0";

    @Override
    public LongDivisionResult calculate(int dividend, int divisor) {
        validate(divisor);

        LongDivisionResult result = new LongDivisionResult();
        result.addData(dividend, divisor, getRemainder(dividend, divisor));
        int subDividend = getFirstDividend(dividend, divisor);

        if (subDividend >= 0 && subDividend < divisor) {
            result.addData(subDividend, 0, subDividend);
            result.addQuotient(0);
            return result;
        }
        return longDivision(subDividend, result);
    }

    private void validate(int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Divisor is 0. Divisor should not be 0.");
        }
    }

    private LongDivisionResult longDivision(Integer subDividend, LongDivisionResult result) {
        Integer dividend = result.getDividends().get(0);
        int divisor = result.getDivisors().get(0);
        int quotientLength = getQuotient(dividend, divisor).toString().length();
        int partialQuotientLength = 1;
        int firstDigit = subDividend.toString().length() - 1;
        int lastDigit = firstDigit;
        StringJoiner quotient = new StringJoiner("");
        int remainder = 0;

        while (partialQuotientLength <= quotientLength) {
            if (subDividend == 0) {
                quotient.add(ZERO);
                firstDigit++;
            } else if (subDividend < divisor) {
                quotient.add(ZERO);
            } else {
                Integer partialQuotient = getQuotient(subDividend, divisor);
                remainder = getRemainder(subDividend, divisor);
                quotient.add(partialQuotient.toString());
                firstDigit = lastDigit + 1;
                result.addData(subDividend, partialQuotient * divisor, remainder);
            }
            lastDigit++;
            if (lastDigit == dividend.toString().length()) {
                break;
            }
            partialQuotientLength++;
            subDividend = Integer.parseInt(Integer.toString(remainder) + dividend.toString()
                .substring(firstDigit, lastDigit + 1));
        }

        result.addQuotient(Integer.parseInt(quotient.toString()));
        return result;
    }

    private int getFirstDividend(int dividend, int divisor) {
        String dividendToString = String.valueOf(dividend);
        int firstDividend = 0;

        for (int i = 0; i < dividendToString.length(); i++) {
            firstDividend = Integer.parseInt(dividendToString.substring(0, i + 1));

            if (firstDividend >= divisor) {
                break;
            }
        }
        return firstDividend;
    }

    private Integer getQuotient(int dividend, int divisor) {
        return dividend / divisor;
    }

    private Integer getRemainder(int dividend, int divisor) {
        return dividend % divisor;
    }
}
