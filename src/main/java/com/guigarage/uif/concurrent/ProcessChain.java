package com.guigarage.uif.concurrent;

import com.guigarage.uif.exception.DefaultExceptionHandler;
import com.guigarage.uif.exception.ExceptionHandler;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ProcessChain<T> {

    private List<ProcessDescription<?, ?>> processes;
    private Executor executorService;
    private ExceptionHandler exceptionHandler;
    private Runnable finalRunnable;

    public ProcessChain() {
        this(Executors.newCachedThreadPool());
    }

    public ProcessChain(Executor executorService) {
        this(executorService, null, DefaultExceptionHandler.getDefaultInstance(), null);
    }

    private ProcessChain(Executor executorService, List<ProcessDescription<?, ?>> processes, ExceptionHandler exceptionHandler, Runnable finalRunnable) {
        this.executorService = executorService;
        this.processes = new ArrayList<>();
        if (processes != null) {
            this.processes.addAll(processes);
        }
    }

    public static ProcessChain<Void> create() {
        return new ProcessChain<>();
    }

    public static ProcessChain<Void> create(Executor executorService) {
        return new ProcessChain<>(executorService);
    }

    public <V> ProcessChain<V> addFunction(Function<T, V> function, ThreadType type) {
        return addProcessDescription(new ProcessDescription<T, V>(function, type));
    }

    public <V> ProcessChain<V> addProcessDescription(ProcessDescription<T, V> processDescription) {
        processes.add(processDescription);
        return new ProcessChain<V>(executorService, processes, exceptionHandler, finalRunnable);
    }

    public <V> ProcessChain<V> addFunctionInPlatformThread(Function<T, V> function) {
        return addFunction(function, ThreadType.PLATFORM);
    }

    public <V> ProcessChain<V> addFunctionInExecutor(Function<T, V> function) {
        return addFunction(function, ThreadType.EXECUTOR);
    }

    public ProcessChain<Void> addRunnable(Runnable runnable, ThreadType type) {
        return addFunction((Function<T, Void>) (e) -> {
            runnable.run();
            return null;
        }, type);
    }

    public ProcessChain<Void> addRunnableInPlatformThread(Runnable runnable) {
        return addRunnable(runnable, ThreadType.PLATFORM);
    }

    public ProcessChain<Void> addRunnableInExecutor(Runnable runnable) {
        return addRunnable(runnable, ThreadType.EXECUTOR);
    }

    public ProcessChain<Void> addConsumer(Consumer<T> consumer, ThreadType type) {
        return addFunction((Function<T, Void>) (e) -> {
            consumer.accept(e);
            return null;
        }, type);
    }

    public ProcessChain<Void> addConsumerInPlatformThread(Consumer<T> consumer) {
        return addConsumer(consumer, ThreadType.PLATFORM);
    }

    public ProcessChain<Void> addConsumerInExecutor(Consumer<T> consumer) {
        return addConsumer(consumer, ThreadType.EXECUTOR);
    }

    public <V> ProcessChain<V> addSupplierInPlatformThread(Supplier<V> supplier) {
        return addSupplier(supplier, ThreadType.PLATFORM);
    }

    public <V> ProcessChain<V> addSupplierInExecutor(Supplier<V> supplier) {
        return addSupplier(supplier, ThreadType.EXECUTOR);
    }

    public <V> ProcessChain<V> addSupplier(Supplier<V> supplier, ThreadType type) {
        return addFunction((Function<T, V>) (e) -> {
            return supplier.get();
        }, type);
    }

    public <V> ProcessChain<List<V>> addPublishingTask(Supplier<List<V>> supplier, Consumer<Publisher<V>> consumer) {
        return addFunction((Function<T, List<V>>) (e) -> {
            List<V> list = supplier.get();
            Publisher<V> publisher = p -> {
                try {
                    UIToolkit.runAndWait(() -> list.addAll(Arrays.asList(p)));
                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                }
            };
            consumer.accept(publisher);
            return list;
        }, ThreadType.EXECUTOR);
    }

    public <V> ProcessChain<List<V>> addPublishingTask(List<V> list, Consumer<Publisher<V>> consumer) {
        return addPublishingTask(() -> list, consumer);
    }

    public ProcessChain<T> onException(ExceptionHandler handler) {
        this.exceptionHandler = handler;
        return this;
    }

    public ProcessChain<T> withFinal(Runnable finalRunnable) {
        this.finalRunnable = finalRunnable;
        return this;
    }

    @SuppressWarnings("unchecked")
    private <U, V> V execute(U inputParameter, ProcessDescription<U, V> processDescription) throws InterruptedException, ExecutionException {
        if (processDescription.getThreadType().equals(ThreadType.EXECUTOR)) {
            return processDescription.getFunction().apply(inputParameter);
        } else {
            return UIToolkit.runCallableAndWait(() -> processDescription.getFunction().apply(inputParameter));
        }
    }

    public Future<T> repeatInfinite() {
        return repeat(Integer.MAX_VALUE);
    }

    public Future<T> repeatInfinite(Duration pauseTime) {
        return repeat(Integer.MAX_VALUE, pauseTime);
    }

    public Future<T> repeat(int count) {
        return repeat(count, Duration.ZERO);
    }

    public Future<T> repeat(int count, Duration pauseTime) {
        Callable<T> callable = () -> {
            try {
                Object lastResult = null;
                if (count == Integer.MAX_VALUE) {
                    while (true) {
                        lastResult = null;
                        for (ProcessDescription<?, ?> processDescription : processes) {
                            lastResult = execute(lastResult, (ProcessDescription<Object, ?>) processDescription);
                        }
                        Thread.sleep((long) pauseTime.toMillis());
                    }
                } else {
                    for (int i = 0; i < count; i++) {
                        lastResult = null;
                        for (ProcessDescription<?, ?> processDescription : processes) {
                            lastResult = execute(lastResult, (ProcessDescription<Object, ?>) processDescription);
                        }
                        Thread.sleep((long) pauseTime.toMillis());
                    }
                }
                return (T) lastResult;
            } catch (Exception e) {
                if (exceptionHandler != null) {
                    UIToolkit.runAndWait(() -> exceptionHandler.setException(e));
                }
                throw e;
            } finally {
                if (finalRunnable != null) {
                    UIToolkit.runAndWait(() -> finalRunnable.run());
                }
            }
        };
        RunnableFuture<T> task = new FutureTask<T>(callable);
        executorService.execute(task);
        return task;
    }

    public Future<T> run() {
        return repeat(1);
    }
}
