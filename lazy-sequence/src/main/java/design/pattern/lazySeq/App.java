package design.pattern.lazySeq;

import org.sandcastle.apps.LazySeq;

/**
 * Application responsible for demonstrating Lazy Sequence design pattern
 */
public class App {
    public static LazySeq<Integer> lastTwoFib(int first, int last) {
        return LazySeq.cons(first, () -> lastTwoFib(last, first + last));
    }
}
