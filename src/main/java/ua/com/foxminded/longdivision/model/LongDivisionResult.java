package ua.com.foxminded.longdivision.model;

import java.util.ArrayList;
import java.util.List;

public class LongDivisionResult {

    private List<Integer> dividends = new ArrayList<>();
    private List<Integer> remainders = new ArrayList<>();
    private List<Integer> divisors = new ArrayList<>();
    private int quotient;

    public void addData(int dividend, int divisor, int remainder) {
        dividends.add(dividend);
        divisors.add(divisor);
        remainders.add(remainder);
    }

    public void addQuotient(int quotient) {
        this.quotient = quotient;
    }

    public Integer getQuotient() {
        return quotient;
    }

    public List<Integer> getDividends() {
        return dividends;
    }

    public List<Integer> getDivisors() {
        return divisors;
    }

    public List<Integer> getRemainders() {
        return remainders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof LongDivisionResult)) {
            return false;
        }
        LongDivisionResult another = (LongDivisionResult) o;
        boolean dividendsEqual = this.dividends.equals(another.getDividends());
        boolean remaindersEqual = this.remainders.equals(another.getRemainders());
        boolean divisorsEqual = this.divisors.equals(another.getDivisors());
        boolean quotientEqual = (this.quotient == another.getQuotient());
        return dividendsEqual && remaindersEqual && divisorsEqual && quotientEqual;
    }

    @Override
    public int hashCode() {
        return dividends.hashCode() + divisors.hashCode() + remainders.hashCode() + Integer.hashCode(quotient);
    }

    @Override
    public String toString() {
        return dividends.toString() + System.lineSeparator() + divisors.toString() + System.lineSeparator()
               + remainders.toString() + System.lineSeparator() + String.valueOf(quotient);  
    }
}
