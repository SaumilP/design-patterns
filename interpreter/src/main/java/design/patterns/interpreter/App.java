package design.patterns.interpreter;

import design.patterns.interpreter.expressions.AndExpression;
import design.patterns.interpreter.expressions.Expression;
import design.patterns.interpreter.expressions.OrExpression;
import design.patterns.interpreter.expressions.TerminalExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client using expressions
 */
public class App {
    private static final Logger Log = LoggerFactory.getLogger(App.class);

    static Expression prepareExpressionTree(){
        Expression terminal1 = new TerminalExpression("John");
        Expression terminal2 = new TerminalExpression("Owen");
        Expression terminal3 = new TerminalExpression("Michael");
        Expression terminal4 = new TerminalExpression("Mary");

        Expression alternation1 = new OrExpression( terminal1, terminal4);

        Expression alternation2 = new OrExpression(terminal2, alternation1);

        return new AndExpression(terminal3, alternation2);
    }

    public static void main( String[] args){
        String context = "Mary Owen";
        Expression define = prepareExpressionTree();
        Log.debug(context + " is " + define.interpret(context));
        System.out.println(context + " is " + define.interpret(context));
    }
}
