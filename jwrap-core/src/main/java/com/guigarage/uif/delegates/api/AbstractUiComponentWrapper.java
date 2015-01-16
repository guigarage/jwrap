package com.guigarage.uif.delegates.api;

/**
 * Created by hendrikebbers on 14.01.15.
 */
public abstract class AbstractUiComponentWrapper<S> implements ViewNodeWrapper<S> {

    private S component;

    protected AbstractUiComponentWrapper(S component) {
        this.component = component;
    }

    @Override
    public S getComponent() {
        return component;
    }
}
