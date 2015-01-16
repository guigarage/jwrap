package com.guigarage.uif.toolkit.ios;

import com.guigarage.uif.concurrent.UiPlatform;
import org.robovm.apple.dispatch.DispatchQueue;

/**
 * Created by hendrikebbers on 15.01.15.
 */
public class IOSPlatform implements UiPlatform {

    @Override
    public void runLater(Runnable runnable) {
        DispatchQueue.getMainQueue().async(runnable);
    }

    @Override
    public boolean isApplicationThread() {
        //TODO
        return false;
    }
}
