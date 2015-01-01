package design.patterns.interpreter.expressions;

import java.util.StringTokenizer;

/**
 * Terminal Expression
 */
public class TerminalExpression extends Expression {

    private String literal = null;

    public TerminalExpression(String literal) {
        this.literal = literal;
    }

    @Override public boolean interpret(String expressionStr) {
        StringTokenizer tokenizer = new StringTokenizer(expressionStr);
        while( tokenizer.hasMoreElements() ){
            String test = tokenizer.nextToken();
            if ( test.equals(literal)){
                return true;
            }
        }
        return false;
    }
}
