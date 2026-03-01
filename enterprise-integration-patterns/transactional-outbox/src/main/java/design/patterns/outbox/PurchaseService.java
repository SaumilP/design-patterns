package design.patterns.outbox;

import java.util.Objects;

/**
 * An example: purchase coins.
 *
 * Domain write (order) and integration event write (outbox) happen in the same transaction.
 */
public final class PurchaseService {
    private final InMemoryDatabase db;

    public PurchaseService(InMemoryDatabase db) {
        this.db = Objects.requireNonNull(db, "db");
    }

    public void purchaseCoins(String playerId, int coins) {
        InMemoryDatabase.Transaction tx = db.begin();
        tx.insertOrder(playerId, coins);
        tx.addOutbox("coins.purchased", "{ \"playerId\": \"" + playerId + "\", \"coins\": " + coins + " }");
        tx.commit();
    }
}

