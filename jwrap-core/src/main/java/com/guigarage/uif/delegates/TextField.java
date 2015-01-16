package com.guigarage.uif.delegates;

import com.guigarage.uif.delegates.api.BasicDelegate;
import com.guigarage.uif.delegates.api.ViewNodeWrapper;

/**
 * Created by hendrikebbers on 14.01.15.
 */
public class TextField<S> extends BasicDelegate<S> implements WithEnabledDelegate<S>, WithTextDelegate<S>, WithVisibleDelegate<S> {

    public TextField(ViewNodeWrapper<S> wrapper) {
        super(wrapper);
    }
}
