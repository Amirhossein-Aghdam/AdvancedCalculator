package com.restadvancedcalculator.calculator.dps;

import com.restadvancedcalculator.calculator.model.Expression;

public interface ExpressionDps {

    double calculateWithoutVarriables(Expression expression);

    double calculateWithVarriables(Expression expression);
}
