package com.guigarage.uif.toolkit.ios;

import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.uikit.UIView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by hendrikebbers on 16.01.15.
 */
public class IOSViewNodeRegistry {

    private final static IOSViewNodeRegistry INSTANCE = new IOSViewNodeRegistry();

    private Map<UIView, List<IOSViewRegistration>> registrations;

    public IOSViewNodeRegistry() {
        registrations = new HashMap<>();
    }

    public void register(UIView mainView, UIView viewNode, String nodeId) {
        Foundation.log("Registering " + viewNode + " with ID " + nodeId + " in View " + mainView);
        List<IOSViewRegistration> listForView = registrations.get(mainView);
        if(listForView == null) {
            listForView = new CopyOnWriteArrayList<>();
            registrations.put(mainView, listForView);
        }
        listForView.add(new IOSViewRegistration(viewNode, nodeId));
    }

    public UIView getRegisteredView(UIView mainView, String nodeId) {
        Foundation.log("Searching for Node with ID " + nodeId + " in View " + mainView);
        List<IOSViewRegistration> listForView = registrations.get(mainView);
        if(listForView != null) {
            for(IOSViewRegistration registration : listForView) {
                if(registration.getId().equals(nodeId)) {
                    return registration.getViewNode();
                }
            }
        }
        return null;
    }

    public static IOSViewNodeRegistry getInstance() {
        return INSTANCE;
    }
}
