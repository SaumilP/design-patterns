package design.patterns.proxy;

/**
 * Client for Proxy Design Pattern Example
 */
public class App {

    public static void main(String[] args) {
        BankAccount privateAccount = new BankAccount(12000.99);
        privateAccount.deposite(900);
        privateAccount.withdraw(2000);

        User tomDickHarry = new User("Tom Dick", "Tom");
        User johnDoe = new User("John Doe", "John");

        AccountProtectionProxy proxy = new AccountProtectionProxy(johnDoe, privateAccount);
        proxy.depositeAmount( tomDickHarry, 2000 );
        try {
            proxy.withdrawAmount( tomDickHarry, 20000);
        } catch (IllegalAccessException e) {
            String errMessage = String.format("!!! Illegal Activity detected !!! - %s", e.getLocalizedMessage());
            System.out.println(errMessage);
        }

        try {
            proxy.withdrawAmount(johnDoe, 15000);
        } catch (IllegalAccessException e) {
            System.out.println("!!! Account Problems !!! - " + e.getMessage());
            e.printStackTrace();
        }
    }
}
