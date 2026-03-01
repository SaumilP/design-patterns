package design.patterns.locators.services;

/**
 * Service Class
 */
public class FindClubService implements BroService {

    public FindClubService() {
        System.out.println("Guy who can help barney in finding best pubs/bars/clubs has been registered");
    }

    @Override public void executeService() {
        System.out.println("Going on troll to find best club in neighbourhood");
    }
}
