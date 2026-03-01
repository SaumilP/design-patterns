package design.patterns.outbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionalOutboxTest {
    @Test
    void publishesEventOnlyAfterCommit() {
        InMemoryDatabase db = new InMemoryDatabase();
        InMemoryMessageBroker broker = new InMemoryMessageBroker();
        OutboxPublisher publisher = new OutboxPublisher(db, broker);

        // Begin a tx but don't commit.
        InMemoryDatabase.Transaction tx = db.begin();
        tx.insertOrder("p1", 100);
        tx.addOutbox("coins.purchased", "{ \"playerId\": \"p1\", \"coins\": 100 }");

        assertEquals(0, publisher.publishOnce(100));
        assertEquals(0, broker.readTopic("coins.purchased").size());

        tx.commit();

        assertEquals(1, publisher.publishOnce(100));
        assertEquals(1, broker.readTopic("coins.purchased").size());
    }

    @Test
    void canPublishMultipleMessagesInBatches() {
        InMemoryDatabase db = new InMemoryDatabase();
        InMemoryMessageBroker broker = new InMemoryMessageBroker();
        PurchaseService service = new PurchaseService(db);
        OutboxPublisher publisher = new OutboxPublisher(db, broker);

        service.purchaseCoins("p1", 10);
        service.purchaseCoins("p1", 20);
        service.purchaseCoins("p1", 30);

        assertEquals(2, publisher.publishOnce(2));
        assertEquals(1, publisher.publishOnce(2));
        assertEquals(3, broker.readTopic("coins.purchased").size());
    }
}

