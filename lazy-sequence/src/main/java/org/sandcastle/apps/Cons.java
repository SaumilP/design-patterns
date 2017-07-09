package org.sandcastle.apps;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Cons<E> extends LazySeq<E> {
    private final E head;
    private volatile LazySeq<E> tailOrNull;
    private final Supplier<LazySeq<E>> tailFun;

    Cons(E head, Supplier<LazySeq<E>> tailFun) {
        this.head = Objects.requireNonNull(head);
        this.tailFun = Objects.requireNonNull(tailFun);
    }

    @Override
    public E head() {
        return head;
    }

    @Override
    public LazySeq<E> tail() {
        if (!isTailDefined()) {
            synchronized (this) {
                if (!isTailDefined()) {
                    tailOrNull = tailFun.get();
                }
            }
        }
        return tailOrNull;
    }

    @Override
    protected boolean isTailDefined() {
        return tailOrNull != null;
    }

    public <R> LazySeq<R> map(Function<? super E, ? extends R> mapper) {
        return cons(mapper.apply(head()), () -> tail().map(mapper));
    }

    @Override
    public LazySeq<E> filter(Predicate<? super E> predicate) {
        if (predicate.test(head)) {
            return cons(head, () -> tail().filter(predicate));
        } else {
            return tail().filter(predicate);
        }
    }

    @Override
    public <R> LazySeq<R> flatMap(Function<? super E, ? extends Iterable<? extends R>> mapper) {
        final ArrayList<R> result = new ArrayList<>();
        mapper.apply(head).forEach(result::add);
        return concat(result, () -> tail().flatMap(mapper));
    }

    @Override
    protected LazySeq<E> takeUnsafe(long maxSize) {
        if (maxSize > 1) {
            return cons(head, () -> tail().takeUnsafe(maxSize - 1));
        } else {
            return LazySeq.of(head);
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
