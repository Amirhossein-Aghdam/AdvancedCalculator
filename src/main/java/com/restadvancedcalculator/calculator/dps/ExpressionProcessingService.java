package com.restadvancedcalculator.calculator.dps;

import com.restadvancedcalculator.calculator.model.Expression;
import com.restadvancedcalculator.calculator.model.Token;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Stack;

@Repository("RegularExpression")
public class ExpressionProcessingService implements ExpressionDps {

    @Override
    public double evaluate(Expression expression) throws Exception {
        return evaluatePostfix(infixToPostfix(encode(tokenize(expression))));
    }

    @Override
    public String convert(Expression expression) throws Exception{
        ArrayList<Token> list = infixToPostfix(encode(tokenize(expression)));
        String res = "";
        for(Token token : list)res+=token.toString();
        return res;
    }

    public ArrayList<Token> encode(ArrayList<Token> list){
        int size = list.size();
        if(list.get(0).isPlusOrMinus()) list.get(0).encode();
        for(int i=0;i<size-1;i++)
            if( (list.get(i).isBeginParenthesis() || list.get(i).isOperator() ) && list.get(i+1).isPlusOrMinus())
                list.get(i+1).encode();
        return list;
    }
    public ArrayList<Token> decode(ArrayList<Token> list){
        for(Token token : list)
            if(token.isEncoded()) token.decode();
        return list;
    }

    public ArrayList<Token> tokenize(Expression expression) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(expression.getInfix()));
        tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');
        ArrayList<Token> tokenBuffer = new ArrayList<>();
        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            switch (tokenizer.ttype) {
                case StreamTokenizer.TT_NUMBER -> tokenBuffer.add(new Token(String.valueOf(tokenizer.nval)));
                case StreamTokenizer.TT_WORD -> tokenBuffer.add(new Token(tokenizer.sval));
                default -> tokenBuffer.add(new Token(String.valueOf((char) tokenizer.ttype)));
            }
        }
        return tokenBuffer;
    }

    public int Prec(Token token) {
        String ch = token.toString();
        return switch (ch) {
            case "+", "-", "@", "#" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> -1;
        };
    }

    public ArrayList<Token> infixToPostfix(ArrayList<Token> list) {
        ArrayList<Token> postfix = new ArrayList<Token>();
        Stack<Token> stack = new Stack<>();
        for (Token token : list) {
            if (token.isNumberOrVar()) postfix.add(token);
            else if(token.isFunction() || token.isUnary()) stack.push(token);
            else if (token.isBeginParenthesis()) stack.push(token);
            else if (token.isEndParenthesis()) {
                while (!stack.isEmpty() &&
                        !stack.peek().toString().contentEquals("("))
                    postfix.add(stack.pop());
                stack.pop();
                if( !stack.isEmpty() && (stack.peek().isFunction() || stack.peek().isUnary() ) )
                    postfix.add(stack.pop());
            }
            else {
                while (!stack.isEmpty() && Prec(token) <= Prec(stack.peek()) )
                    postfix.add(stack.pop());
                stack.push(token);
            }
        }
        while (!stack.isEmpty()){
            if(stack.peek().toString().contentEquals("("))
                return null;
            postfix.add(stack.pop());
        }
        return postfix;
    }

    public double evaluatePostfix(ArrayList<Token> list)
    {
        Stack<String> stack = new Stack<>();
        for(Token c : list) {
            if( c.isNumberOrVar() ) stack.push(c.toString());
            else if ( c.isFunction() ){
                Double val = Double.parseDouble(stack.pop());
                switch (c.toString()) {
                    case "log" -> stack.push(Double.toString(Math.log10(val)));
                    case "sin" -> stack.push(Double.toString(Math.sin(val)));
                    case "cos" -> stack.push(Double.toString(Math.cos(val)));
                    case "tan" -> stack.push(Double.toString(Math.tan(val)));
                    case "cot" -> stack.push(Double.toString(1 / Math.tan(val)));
                    case "sec" -> stack.push(Double.toString(1 / Math.cos(val)));
                    case "csc" -> stack.push(Double.toString(1 / Math.sin(val)));
                    case "abs" -> stack.push(Double.toString(Math.abs(val)));
                }
            }
            else if(c.isUnary()){
                Double val = Double.parseDouble(stack.pop());
                switch (c.toString()) {
                    case "@" -> stack.push(Double.toString(val));
                    case "#" -> stack.push(Double.toString(-val));
                }
            }
            else {
                Double val1 = Double.parseDouble(stack.pop());
                Double val2 = Double.parseDouble(stack.pop());
                switch (c.toString()) {
                    case "+" -> stack.push(Double.toString(val2 + val1));
                    case "-" -> stack.push(Double.toString(val2 - val1));
                    case "/" -> stack.push(Double.toString(val2 / val1));
                    case "*" -> stack.push(Double.toString(val2 * val1));
                    case "^" -> stack.push(Double.toString(Math.pow(val2, val1)));
                }
            }
        }
        return Double.parseDouble(stack.pop());
    }
}
