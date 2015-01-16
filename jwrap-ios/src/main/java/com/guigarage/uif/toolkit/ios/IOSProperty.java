package com.guigarage.uif.toolkit.ios;

import com.guigarage.uif.delegates.api.AbstractProperty;
import org.robovm.apple.foundation.NSKeyValueChangeInfo;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;

/**
 * Created by hendrikebbers on 15.01.15.
 */
public class IOSProperty<T> extends AbstractProperty<T> {

    private NSObject object;

    private String keyPath;

    public IOSProperty(NSObject object, String keyPath) {
        this.object = object;
        this.keyPath = keyPath;
        object.addKeyValueObserver(keyPath, new NSObject.NSKeyValueObserver() {
            @Override
            public void observeValue(String keyPath, NSObject object, NSKeyValueChangeInfo change) {
                fireValueChanged((T) change.getOld(), (T) change.getNew());
            }
        });
    }

    @Override
    public void set(T value) {
        if(value != null && value instanceof String) {
            object.getKeyValueCoder().setValue(keyPath, new NSString(value.toString()));
        } else {
            object.getKeyValueCoder().setValue(keyPath, (NSObject) value);
        }
    }

    @Override
    public T get() {
        Object nativeValue = object.getKeyValueCoder().getValue(keyPath);
        if(nativeValue == null) {
            return null;
        }
        if(nativeValue instanceof NSString) {
            return (T) nativeValue.toString();
        }
        return (T) nativeValue;
    }

}
