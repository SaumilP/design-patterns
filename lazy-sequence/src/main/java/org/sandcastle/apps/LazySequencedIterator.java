package org.sandcastle.apps;

import java.util.Iterator;
import java.util.function.Consumer;

public class LazySequencedIterator<E> implements Iterator<E> {

    private LazySeq<E> underlying;

    public LazySequencedIterator(LazySeq<E> lazySeq) {
        this.underlying = lazySeq;
    }

    @Override
    public boolean hasNext() {
        return !underlying.isEmpty();
    }

    @Override
    public E next() {
        final E next = underlying.head();
        underlying = underlying.tail();
        return next;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        underlying.forEach(action);
    }
}
