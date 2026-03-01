package design.patterns.locators;

import design.patterns.locators.bros.BarneyGuy;
import design.patterns.locators.bros.ClubGuy;
import design.patterns.locators.bros.GuyFinderGuy;
import design.patterns.locators.bros.ShoeGuy;
import design.patterns.locators.services.ServiceType;

/**
 * Service Locator Design Pattern Client
 */
public class App {
    public static void main(String[] args){
        BarneyGuy john = new ClubGuy("John Doe", "012345678");
        john.addServiceType(ServiceType.FIND_A_CLUB);

        BarneyGuy hurricane = new GuyFinderGuy("Hurricane Stinson", "012345678");
        hurricane.addServiceType(ServiceType.FIND_A_GUY);

        BarneyGuy ted = new ShoeGuy("Ted Mosby", "012345678");
        ted.addServiceType(ServiceType.HIRE_SHOES);

        ServiceLocator.AddGuyForService( john );
        ServiceLocator.AddGuyForService( hurricane );
        ServiceLocator.AddGuyForService(ted);
         System.out.println("\nThis is going to be - wait for it .......... LEGENDARY");
        ServiceLocator.callBroForService(ServiceType.FIND_A_CLUB);
    }
}
