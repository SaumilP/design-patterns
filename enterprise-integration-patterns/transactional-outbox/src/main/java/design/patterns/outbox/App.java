package design.patterns.outbox;

public final class App {
    public static void main(String[] args) {
        InMemoryDatabase db = new InMemoryDatabase();
        InMemoryMessageBroker broker = new InMemoryMessageBroker();
        PurchaseService service = new PurchaseService(db);
        OutboxPublisher publisher = new OutboxPublisher(db, broker);

        service.purchaseCoins("player-42", 500);
        publisher.publishOnce(100);

        System.out.println(broker.readTopic("coins.purchased"));
    }
}

