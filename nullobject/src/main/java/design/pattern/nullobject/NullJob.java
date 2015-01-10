package design.pattern.nullobject;

/**
 * Null Job Object
 */
public class NullJob extends Job {

    protected NullJob() {
        super(0);
    }

    @Override public boolean isNull() {
        return true;
    }
}
