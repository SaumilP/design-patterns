package design.patterns.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args){
        /** Expression := 1 AND 2 */
        Expression expression = new AndComposite();
        expression.add(new Variable("1"));
        expression.add(new Variable("2"));
        expression.set("1", true).set("2", false);
        log.debug("Expression[{}] Result :=> {}", expression.toString(), expression.evaluate() );

        /** Chained Expression := 1 OR (2 AND 3) */
        Expression chainExpression = ( new OrComposite() )
                .add(new Variable("1"))
                .add((new AndComposite())
                     .add(new Variable("2"))
                     .add(new Variable("3"))
                    )
                .set("1", false)
                .set("2", true)
                .set("3", false);

        log.debug("Chained Expression[{}] Result :=> {}", chainExpression.toString(), chainExpression.evaluate());
    }
}
