package com.guigarage.uif.concurrent;

public class ProcessDescription<V, T> {

    private ProcessFunction<V, T> function;

    private ThreadType threadType;

    public ProcessDescription(ProcessFunction<V, T> function, ThreadType threadType) {
        this.function = function;
        this.threadType = threadType;
    }

    public ProcessFunction<V, T> getFunction() {
        return function;
    }

    public ThreadType getThreadType() {
        return threadType;
    }
}
