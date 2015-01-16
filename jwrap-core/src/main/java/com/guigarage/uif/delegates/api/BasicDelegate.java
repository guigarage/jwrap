package com.guigarage.uif.delegates.api;

/**
 * Created by hendrikebbers on 14.01.15.
 */
public class BasicDelegate<S> implements ViewNodeDelegate<S> {

    private ViewNodeWrapper<S> wrapper;

    public BasicDelegate(ViewNodeWrapper<S> wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public ViewNodeWrapper<S> getUiComponentWrapper() {
        return wrapper;
    }
}
