package com.guigarage.uif.concurrent;

/**
 * Created by hendrikebbers on 16.01.15.
 */
public interface ProcessFunction<T, R> {

    R call(T t);
}