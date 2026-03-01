package design.patterns.saga;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class App {
    public static void main(String[] args) {
        // An example: purchase an item with inventory + wallet as separate resources.
        AtomicBoolean inventoryReserved = new AtomicBoolean(false);
        AtomicInteger walletBalance = new AtomicInteger(100);
        AtomicBoolean itemGranted = new AtomicBoolean(false);

        Saga saga = Saga.builder("purchase-legendary-sword")
                .step(
                        "reserve-inventory",
                        () -> inventoryReserved.set(true),
                        () -> inventoryReserved.set(false)
                )
                .step(
                        "charge-wallet",
                        () -> {
                            int price = 60;
                            if (walletBalance.get() < price) {
                                throw new RuntimeException("Insufficient funds");
                            }
                            walletBalance.addAndGet(-price);
                        },
                        () -> walletBalance.addAndGet(60)
                )
                .step(
                        "grant-item",
                        () -> itemGranted.set(true),
                        () -> itemGranted.set(false)
                )
                .build();

        Saga.Result result = saga.run();
        System.out.println("success=" + result.success());
        result.log().forEach(System.out::println);
        System.out.println("inventoryReserved=" + inventoryReserved.get()
                + " walletBalance=" + walletBalance.get()
                + " itemGranted=" + itemGranted.get());
    }
}

