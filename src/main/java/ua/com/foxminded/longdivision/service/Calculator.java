package ua.com.foxminded.longdivision.service;

import ua.com.foxminded.longdivision.model.LongDivisionResult;

public interface Calculator<T extends LongDivisionResult> {

    T calculate(int dividend, int divisor);
}
