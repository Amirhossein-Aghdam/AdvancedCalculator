package com.restadvancedcalculator.calculator.service;

import com.restadvancedcalculator.calculator.dps.ExpressionDps;
import com.restadvancedcalculator.calculator.model.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ExpressionService {
    private final ExpressionDps expressionDps;

    @Autowired
    public ExpressionService(@Qualifier("RegularExpression") ExpressionDps expressionDps) {
        this.expressionDps = expressionDps;
    }
    public Double evaluate(Expression expression) throws Exception{
        return expressionDps.evaluate(expression);
    }
    public String convert(Expression expression)throws Exception{
        return expressionDps.convert(expression);
    }
}
