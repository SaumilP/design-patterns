package design.patterns.locators.services;

/**
 * Service Class
 */
public class FindShoeService implements BroService {

    public FindShoeService() {
        System.out.println("Guy who can help barney in finding shoe shops has been registered");
    }

    @Override public void executeService() {
        System.out.println("Going on troll to find best shoe shops in neighbourhood");
    }
}
