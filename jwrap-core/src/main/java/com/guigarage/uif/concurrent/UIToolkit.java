package com.guigarage.uif.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class UIToolkit {

    private static UiPlatform platform;

    public static void runAndWait(Runnable runnable)
            throws InterruptedException, ExecutionException {
        platform.runAndWait(runnable);
    }

    public static void runLater(Runnable runnable) {
        platform.runLater(runnable);
    }

    public static boolean isToolkitThread() {
        return platform.isApplicationThread();
    }

    public static <T> T runCallableAndWait(Callable<T> callable)
            throws InterruptedException, ExecutionException {
        FutureTask<T> future = new FutureTask<T>(callable);
        runLater(future);
        return future.get();
    }

    public static UiPlatform getPlatform() {
        return platform;
    }

    public static void setPlatform(UiPlatform platform) {
        UIToolkit.platform = platform;
    }
}
