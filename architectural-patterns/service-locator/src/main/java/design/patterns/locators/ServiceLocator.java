package design.patterns.locators;

import design.patterns.locators.bros.BarneyGuy;
import design.patterns.locators.services.ServiceType;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Locator Static Class
 */
public class ServiceLocator {
    public static List<BarneyGuy> guys = new ArrayList<>();

    public static BarneyGuy AddGuyForService(BarneyGuy guy){
        if( guys != null ){
            guys.add(guy);
        }
        return guy;
    }

    public static void callBroForService(ServiceType serviceType){
        for (BarneyGuy guy : guys) {
            if ( guy.offersService(serviceType ) ){
                guy.callService();
            }
        }
    }
}
