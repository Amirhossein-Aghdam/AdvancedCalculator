package com.restadvancedcalculator.calculator.model;

import java.util.ArrayList;
import java.util.Arrays;


public class Token {

    private String value;

    public Token(String value){
        this.value = value;
    }
    public boolean isNumberOrVar(){
        return value.matches("-?\\d+(\\.\\d+)?") || value.matches("[a-zA-Z]");
    }
    public boolean isFunction(){
        ArrayList<String> functions = new ArrayList<String>(Arrays.asList(
                "log","sin","cos","tan","cot","sec","csc","abs"));
        for(String function : functions)
            if(value.matches(function))
                return true;
        return false;
    }

    public void encode(){
        if(value.contentEquals("+")) value = "@";
        else if (value.contentEquals("-")) value = "#";
    }

    public void decode(){
        if(value.contentEquals("@")) value = "+";
        else if (value.contentEquals("#")) value = "-";
    }
    public boolean isPlusOrMinus(){
        return value.contentEquals("+") || value.contentEquals("-");
    }
    public boolean isEncoded(){
        return value.contentEquals("@") || value.contentEquals("#");
    }

    // NOTE :
    // Unary for " - " is " # "
    // Unary for " + " is " @ "
    public boolean isUnary(){
        return (value.contentEquals("@") || value.contentEquals("#"));
    }
    public boolean isOperator(){
        return value.contentEquals("+") || value.contentEquals("-") || value.contentEquals("*") ||
                value.contentEquals("/")|| value.contentEquals("^") || value.contentEquals("@")
                || value.contentEquals("#");
    }
    public boolean isBeginParenthesis(){ return value.contentEquals("("); }
    public boolean isEndParenthesis(){return value.contentEquals(")");}

    @Override
    public String toString() {
        return value;
    }
}
