package com.guigarage.uif.concurrent;

@FunctionalInterface
public interface Publisher<T> {

    void publish(final T... values);
}
