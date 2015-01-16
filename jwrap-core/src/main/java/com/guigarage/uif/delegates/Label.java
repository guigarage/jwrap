package com.guigarage.uif.delegates;

import com.guigarage.uif.delegates.api.BasicDelegate;
import com.guigarage.uif.delegates.api.ViewNodeWrapper;

/**
 * Created by hendrikebbers on 15.01.15.
 */
public class Label<S> extends BasicDelegate<S> implements WithEnabledDelegate<S>, WithTextDelegate<S>, WithVisibleDelegate<S> {

    public Label(ViewNodeWrapper<S> wrapper) {
        super(wrapper);
    }
}
