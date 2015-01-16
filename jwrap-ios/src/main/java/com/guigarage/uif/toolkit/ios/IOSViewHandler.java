package com.guigarage.uif.toolkit.ios;

import com.guigarage.uif.delegates.api.ViewNodeWrapper;
import com.guigarage.uif.mvc.ActionEvent;
import com.guigarage.uif.mvc.ActionHandler;
import com.guigarage.uif.mvc.PlatformViewHandler;
import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.uikit.UIButton;
import org.robovm.apple.uikit.UIEvent;
import org.robovm.apple.uikit.UIView;

/**
 * Created by hendrikebbers on 15.01.15.
 */
public class IOSViewHandler implements PlatformViewHandler<UIView> {

    @Override
    public <U extends UIView> ViewNodeWrapper<U> createWrapper(U component) {
        return new IOSNodeWrapper<>(component);
    }

    @Override
    public <U extends UIView, W extends UIView> U findChildById(W parent, String id) {
        return (U) IOSViewNodeRegistry.getInstance().getRegisteredView(parent, id);
    }

    @Override
    public void registerActionListener(ActionHandler listener, String actionId, Object controller) {
        UIView actionTrigger = findChildByActionId(controller, actionId);
        Foundation.log("Found node as Action Trigger for id " + actionId + ": " + actionTrigger);
        if(actionTrigger != null) {
            if(actionTrigger instanceof UIButton) {
                Foundation.log("Adding toucDownListener to Node " + actionTrigger);
                ((UIButton) actionTrigger).addOnTouchUpInsideListener((c, e) -> {
                    Foundation.log("Calling action " + actionId);
                    listener.handleAction(new ActionEvent<UIEvent>(e));
                });
            }
        }
    }

    @Override
    public Class<UIView> getBasicViewClass() {
        return UIView.class;
    }
}
