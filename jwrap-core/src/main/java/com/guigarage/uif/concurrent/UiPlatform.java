package com.guigarage.uif.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public interface UiPlatform {

    default void runAndWait(Runnable runnable)
            throws InterruptedException, ExecutionException {
        FutureTask<Void> future = new FutureTask<>(runnable, null);
        runLater(future);
        future.get();
    }

    void runLater(Runnable runnable);

    boolean isApplicationThread();
}
