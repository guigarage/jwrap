package com.guigarage.uif.exception;

/**
 * Created by hendrikebbers on 24.10.14.
 */
@FunctionalInterface
public interface ExceptionHandler {
    void setException(Throwable exception);
}
