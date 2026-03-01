package design.patterns.adapter.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by PATEL1 on 12/27/14.
 */
public class GoblinGlider {
    private static final Logger Log = LoggerFactory.getLogger(GoblinGlider.class);

    public void attachGlider(){
        Log.debug("Glider attached.");
    }

    public void gainSpeed(){
        Log.debug("Gaining speed.");
    }

    public void takeOff(){
        Log.debug("Lift-off!!!");

    }
}
