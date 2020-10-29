package com.restadvancedcalculator.calculator.api;

import com.restadvancedcalculator.calculator.model.Expression;
import com.restadvancedcalculator.calculator.service.ExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/expression")
@RestController
public class ExpressionController {

    private final ExpressionService expressionService;

    @Autowired
    public ExpressionController(ExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    @PostMapping
    public String evaluate(@RequestBody Expression expression) {
        try {
            return Double.toString( expressionService.evaluate(expression) );
        }
        catch(Exception exception){
            return "Invalid Input.";
        }
    }
    @PostMapping(path="convert")
    public String convert(@RequestBody Expression expression) {
        try {
            return expressionService.convert(expression);
        }
        catch(Exception e){
            return "Invalid Input.";
        }
    }
}
