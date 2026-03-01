package design.patterns.locators.services;

/**
 * Service Class
 */
public class FindSuiteService implements BroService {

    public FindSuiteService() {
        System.out.println("Guy who can help barney find tailor shops has been registered");
    }

    @Override public void executeService() {
        System.out.println("Going on troll to find best tailors in neighbourhood");
    }
}
