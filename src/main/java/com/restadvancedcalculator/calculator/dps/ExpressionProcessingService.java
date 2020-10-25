package com.restadvancedcalculator.calculator.dps;

import com.restadvancedcalculator.calculator.model.Expression;
import org.springframework.stereotype.Repository;

@Repository("RegularExpression")
public class ExpressionProcessingService implements ExpressionDps {

    public ExpressionProcessingService() {
    }

    @Override
    public double calculateWithoutVarriables(Expression expression) {
        return 0;
    }

    @Override
    public double calculateWithVarriables(Expression expression) {
        return 0;
    }
}
