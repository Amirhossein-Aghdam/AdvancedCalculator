package com.restadvancedcalculator.calculator.dps;

import com.restadvancedcalculator.calculator.model.Expression;

public interface ExpressionDps {

    double evaluate(Expression expression) throws Exception;
    String convert(Expression expression) throws Exception;
}
