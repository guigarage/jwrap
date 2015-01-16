package com.guigarage.uif.delegates.api;

public interface ViewDelegate<S> {

    S getRootNode();

    <T extends ViewNodeDelegate<S>> T getViewNode(String id);
}
