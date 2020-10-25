package com.restadvancedcalculator.calculator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public class Expression {

    @NonNull
    private final String expression;
    @NonNull
    private final boolean hasVarriables;
    private final String[] varriables;
    private final float[] varriablesValue;

    public Expression(@JsonProperty("expression") String expression,
                      @JsonProperty("hasVarriables") boolean hasVarriables,
                      @JsonProperty("varriables") String[] varriables,
                      @JsonProperty("varriablesValue") float[] varriablesValue) {

        this.expression = expression;
        this.hasVarriables = hasVarriables;
        this.varriables = varriables;
        this.varriablesValue = varriablesValue;
    }

    @NonNull
    public String getExpression() {
        return expression;
    }

    public boolean getHasVarriables() {
        return hasVarriables;
    }

    public String[] getVarriables() {
        return varriables;
    }

    public float[] getVarriablesValue() {
        return varriablesValue;
    }
}
