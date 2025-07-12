package com.petr.spring.spel.Bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SimpleSpelExamples {

    @Value("#{4+2}")
    private int numEval;

    @Value("#{'I am a'.concat('string')}")
    private String strEval;

    @Value("#{2 > 3 and 1 < 10}")
    private boolean boolEval;

    @Value("#{1 != 1 ? 0 : 1}")
    private int ternary;

    @Value("#{'${my.prop}' ?: 'defaultValue'}")
    private String defaultValue;

    @Value("${app.os.name}")
    private String osName;

    public void simpleEval() {
        System.out.println("Number: " + numEval);
        System.out.println("String: " + strEval);
        System.out.println("Boolean: " + boolEval);
        System.out.println("Ternary: " + ternary);
        System.out.println("Default value: " + defaultValue);
        System.out.println("Os name: " + osName);

    }

    public void simpleEvaluateContextExample(){
        readOnlyContext();
        System.out.println("----------------");
    }

    private void readOnlyContext(){
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

        ExpressionParserObject expressionParserObject = new ExpressionParserObject();

        EvaluationContext readOnlyContext = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        Expression firstStrValueExpression = spelExpressionParser.parseExpression("strValues[0]");

        String firstFromList = firstStrValueExpression.getValue(readOnlyContext, expressionParserObject,String.class);

        Expression secondFromMap = spelExpressionParser.parseExpression("strValues[2]");

        System.out.println("Second form map: "+ secondFromMap.getValue(readOnlyContext,expressionParserObject,String.class));
    }

    private void simpleReadWriteContext(){

        SpelExpressionParser parser = new SpelExpressionParser();

        ExpressionParserObject object= new ExpressionParserObject();

        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        Expression getListExp = parser.parseExpression("strValues");
        List<String> values = getListExp.getValue(context,object,List.class);
        values.add("four");

        System.out.println("Object list: "+object.getStrValues());

        Expression changeFirstValueExp = parser.parseExpression("keyValues[1]");
        changeFirstValueExp.setValue(context,object,String.class);
    }

    private static class ExpressionParserObject {

        private List<String> strValues = new ArrayList<>(List.of("one", "two","three"));
        private Map<Integer,String> keyValues = new HashMap<>(Map.of(1,"one", 2,"two", 3,"three"));

        public List<String> getStrValues() {
            return strValues;
        }

        public Map<Integer, String> getKeyValues() {
            return keyValues;
        }

    }

}
