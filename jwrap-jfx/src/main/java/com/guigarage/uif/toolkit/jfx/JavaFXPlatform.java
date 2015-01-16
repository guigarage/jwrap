package com.guigarage.uif.toolkit.jfx;

import com.guigarage.uif.concurrent.UiPlatform;
import javafx.application.Platform;

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
