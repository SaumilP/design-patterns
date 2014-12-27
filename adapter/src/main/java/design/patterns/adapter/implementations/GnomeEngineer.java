package design.patterns.adapter.implementations;

import design.patterns.adapter.interfaces.Engineer;
import design.patterns.adapter.models.GoblinGlider;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class GnomeEngineer implements Engineer {

    private GoblinGlider glider;

    public GnomeEngineer() {
        glider = new GoblinGlider();
    }

    @Override
    public void operateDevice() {
        glider.attachGlider();
        glider.gainSpeed();
        glider.takeOff();
    }
}
