package com.guigarage.uif.toolkit.ios;

import com.guigarage.uif.delegates.api.AbstractUiComponentWrapper;
import com.guigarage.uif.delegates.api.Property;
import org.robovm.apple.uikit.UIView;

/**
 * Created by hendrikebbers on 15.01.15.
 */
public class IOSNodeWrapper<S extends UIView> extends AbstractUiComponentWrapper<S> {

    protected IOSNodeWrapper(S component) {
        super(component);
    }

    @Override
    public <T> Property<T> getPropertyForName(String propertyName) {
        return new IOSProperty<T>(getComponent(), propertyName);
    }

    @Override
    public boolean isPropertySupported(String propertyName) {
        return true;
    }
}
