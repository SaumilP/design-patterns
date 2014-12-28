package design.patterns.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/28/14.
 */
public abstract class Target {
    private static final Logger Log = LoggerFactory.getLogger(Target.class);


    private Size size;
    private Visibility visibility;

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    @Override public abstract String toString();

    public void printStatus(){
        Log.debug(String.format("%s, Size=%s, Visibility=%s", this, getSize(), getVisibility()));
    }
}
