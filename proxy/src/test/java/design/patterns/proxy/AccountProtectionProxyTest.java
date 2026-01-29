package design.patterns.proxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for AccountProtectionProxy.
 */
public class AccountProtectionProxyTest {
    /**
     * Verifies {@code shouldRejectZeroDepositAmount}.
     */
    @Test
    public void shouldRejectZeroDepositAmount() {
        BankAccount account = new BankAccount(100);
        User owner = new User("Owner", "Owner");
        AccountProtectionProxy proxy = new AccountProtectionProxy(owner, account);

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> proxy.depositeAmount(owner, 0));

        assertEquals("Amount must be more than zero", error.getMessage());
    }

    /**
     * Verifies {@code shouldAllowOwnerToCheckBalance}.
     */
    @Test
    public void shouldAllowOwnerToCheckBalance() throws Exception {
        BankAccount account = new BankAccount(250.0);
        User owner = new User("Owner", "Owner");
        AccountProtectionProxy proxy = new AccountProtectionProxy(owner, account);

        double balance = proxy.checkBalance(owner);

        assertEquals(250.0, balance, 0.0);
    }

    /**
     * Verifies {@code shouldRejectUnauthorizedUser}.
     */
    @Test
    public void shouldRejectUnauthorizedUser() {
        BankAccount account = new BankAccount(250.0);
        User owner = new User("Owner", "Owner");
        User intruder = new User("Other", "Other");
        AccountProtectionProxy proxy = new AccountProtectionProxy(owner, account);

        assertThrows(IllegalAccessException.class, () -> proxy.checkBalance(intruder));
    }
}
