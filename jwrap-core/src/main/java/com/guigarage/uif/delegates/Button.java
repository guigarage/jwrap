package com.guigarage.uif.delegates;

import com.guigarage.uif.delegates.api.BasicDelegate;
import com.guigarage.uif.delegates.api.Property;
import com.guigarage.uif.delegates.api.ViewNodeWrapper;

import java.util.function.Supplier;

/**
 * Created by hendrikebbers on 11.01.15.
 */
public class Button<S> extends BasicDelegate<S> implements WithTextDelegate<S>, WithVisibleDelegate<S>, WithEnabledDelegate<S>, WithOnAction<S> {

    public Button(ViewNodeWrapper<S> delegate) {
        super(delegate);
    }
}
