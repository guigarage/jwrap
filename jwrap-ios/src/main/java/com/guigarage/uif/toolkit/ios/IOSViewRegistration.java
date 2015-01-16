package com.guigarage.uif.toolkit.ios;

import org.robovm.apple.uikit.UIView;

/**
 * Created by hendrikebbers on 16.01.15.
 */
public class IOSViewRegistration {

    private UIView viewNode;

    private String id;

    public IOSViewRegistration(UIView viewNode, String id) {
        this.viewNode = viewNode;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public UIView getViewNode() {
        return viewNode;
    }
}
