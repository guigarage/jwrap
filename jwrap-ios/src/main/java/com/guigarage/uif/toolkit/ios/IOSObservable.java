package com.guigarage.uif.toolkit.ios;

import com.guigarage.uif.delegates.api.AbstractObservable;
import org.robovm.apple.foundation.NSMutableString;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;

/**
 * Created by hendrikebbers on 15.01.15.
 */
public class IOSObservable<T> extends AbstractObservable<T> {

    private NSObject object;

    private String keyPath;

    public IOSObservable(NSObject object, String keyPath) {
        this.object = object;
        this.keyPath = keyPath;
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
