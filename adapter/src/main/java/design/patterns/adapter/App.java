package design.patterns.adapter;

import design.patterns.adapter.implementations.GnomeEngineeringManager;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class App {
    public static void main(String[] args){
        GnomeEngineeringManager manager = new GnomeEngineeringManager();
        manager.operateDevice();
    }
}
