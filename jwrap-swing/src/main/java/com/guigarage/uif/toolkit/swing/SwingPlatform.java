package com.guigarage.uif.toolkit.swing;

import com.guigarage.uif.concurrent.UiPlatform;

import javax.swing.*;

/**
 * Created by hendrikebbers on 24.10.14.
 */
public class SwingPlatform implements UiPlatform {

    private static SwingPlatform instance = new SwingPlatform();

    public SwingPlatform() {
    }

    public static SwingPlatform getInstance() {
        return instance;
    }

    @Override
    public void runLater(Runnable runnable) {
        SwingUtilities.invokeLater(runnable);
    }

    @Override
    public boolean isApplicationThread() {
        return SwingUtilities.isEventDispatchThread();
    }
}
