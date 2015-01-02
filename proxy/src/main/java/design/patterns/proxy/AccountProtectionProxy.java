package design.patterns.proxy;

/**
 * Proxy class to apply restrictions
 */
public class AccountProtectionProxy {
    private User owner;
    private BankAccount account;

    public AccountProtectionProxy(User owner, BankAccount account) {
        this.owner = owner;
        this.account = account;
        System.out.println("Account Protection Proxy created for " + owner.getDisplayName() );
    }

    // OPERATIONS
    public void depositeAmount(User depositor, double amount) throws IllegalArgumentException {
        if( amount == 0 ){
            throw new IllegalArgumentException("Amount must be more than zero");
        }
        account.deposite(amount);
        System.out.println(String.format("> %.2f amount deposited by %s", amount, depositor.getDisplayName()));
    }

    public void withdrawAmount(User transactUser, double amount) throws IllegalAccessException{
        if( isAuthorised(transactUser) ){
            account.withdraw(amount);
            System.out.println(amount + " amount withdrawn by %s" + transactUser.getDisplayName());
        }
    }

    public double checkBalance(User enquiryUser) throws IllegalAccessException {
        if( isAuthorised(enquiryUser)){
            return account.getBalance();
        }
        return 0;
    }

    private boolean isAuthorised(User trsactUser) throws IllegalAccessException {
        // stupid enough but temporarily only owner is allowed to withdraw amount
        if( owner == null || !trsactUser.getName().equals( owner.getName() ) ){
            throw new IllegalAccessException("Illegal Access: %s cannot access account." + trsactUser.getName());
        }
        return true;
    }

    // ACCESSORS
    public User getOwner() {
        return owner;
    }

    public void setOwnerName(User owner) {
        this.owner = owner;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }
}
