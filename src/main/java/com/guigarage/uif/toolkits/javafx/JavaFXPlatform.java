package com.guigarage.uif.toolkits.javafx;

import com.guigarage.uif.concurrent.UiPlatform;
import javafx.application.Platform;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by hendrikebbers on 24.10.14.
 */
public class JavaFXPlatform implements UiPlatform {

    private static JavaFXPlatform instance = new JavaFXPlatform();

    public static JavaFXPlatform getInstance() {
        return instance;
    }

    @Override
    public void runLater(Runnable runnable) {
        Platform.runLater(runnable);
    }

    @Override
    public boolean isApplicationThread() {
        return Platform.isFxApplicationThread();
    }
}
