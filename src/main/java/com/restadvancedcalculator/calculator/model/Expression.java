package com.restadvancedcalculator.calculator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

public class Expression {

    private String infix;
    @Nullable
    private String postfix;
    @Nullable
    private Double value;

    public Expression(@JsonProperty("infix") String infix){
        this.infix = infix;
        this.postfix = postfix;
        this.value = value;
    }

    public String getInfix() {
        return infix;
    }

    public void setInfix(String infix) {
        this.infix = infix;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
