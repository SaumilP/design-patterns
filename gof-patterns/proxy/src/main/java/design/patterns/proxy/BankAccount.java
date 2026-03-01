package design.patterns.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class responsible for performing account specific operations
 */
public class BankAccount {
    private static final Logger log = LoggerFactory.getLogger(BankAccount.class);

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    //OPERATIONS
    public void deposite(double depositAmount){
        balance += depositAmount;
        String message = String.format(" >>> Amount[%.2f] deposited", depositAmount);
        log.debug(message);
    }

    public void withdraw(double withdrawalAmount){
        balance -= withdrawalAmount;
        String message = String.format(" <<< Amount[%.2f] withdrawn", withdrawalAmount);
        log.debug(message);
    }

    // ACCESSORS
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
