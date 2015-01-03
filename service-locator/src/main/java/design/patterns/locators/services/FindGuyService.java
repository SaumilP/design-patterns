package design.patterns.locators.services;

/**
 * Service Class
 */
public class FindGuyService implements BroService {

    public FindGuyService() {
        System.out.println("Guy who can help barney in finding guys - who knows guys who can help providing services - has been registered");
    }

    @Override public void executeService() {
        System.out.println("contacting a guy who knows a guy for requested service");
    }
}
