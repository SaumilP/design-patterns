package design.patterns.composite;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class OrComposite extends Composite {

    @Override public Boolean evaluate() {
        for (Expression child : children) {
            if ( child.evaluate() ){
                return true;
            }
        }
        return false;
    }
}
