package com.guigarage.uif.concurrent;

public interface Publisher<T> {

    void publish(final T... values);
}
