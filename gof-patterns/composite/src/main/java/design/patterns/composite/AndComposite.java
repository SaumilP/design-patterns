package design.patterns.composite;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class AndComposite extends Composite {

    @Override public Boolean evaluate() {
        if ( children != null && !children.isEmpty() ){
            for (Expression child : children) {
                if ( child != null && !child.evaluate() ){
                    return false;
                }
            }
        }
        return true;
    }
}
