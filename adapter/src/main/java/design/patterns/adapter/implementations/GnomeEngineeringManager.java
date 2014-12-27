package design.patterns.adapter.implementations;

import design.patterns.adapter.interfaces.Engineer;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class GnomeEngineeringManager implements Engineer {
    private Engineer engineer;

    public GnomeEngineeringManager() {
        engineer = new GnomeEngineer();
    }

    @Override
    public void operateDevice() {
        engineer.operateDevice();
    }
}
