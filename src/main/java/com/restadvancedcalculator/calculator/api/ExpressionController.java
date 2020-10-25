package com.restadvancedcalculator.calculator.api;

import com.restadvancedcalculator.calculator.model.Expression;
import com.restadvancedcalculator.calculator.service.ExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/expression")
@RestController
public class ExpressionController {

    private final ExpressionService expressionService;

    @Autowired
    public ExpressionController(ExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    @PostMapping
    public double calculate(@RequestBody Expression expression){
        if(expression.getHasVarriables()){
            return expressionService.calculateWithVarriables(expression);
        }
        return expressionService.calculateWithoutVarriables(expression);
    }

}
