package com.mimogoods.dev.tools.setp;

import java.util.function.BiFunction;

public class ExceptionHandlerStep<T> extends DependableStep<T> {
    private final BiFunction<T, Throwable, T> functionWithException;

    protected ExceptionHandlerStep(String name, BiFunction<T, Throwable, T> functionWithException) {
        super(name);
        this.functionWithException = functionWithException;
    }

    @Override
    public T execute(T input, Throwable e) {
        return functionWithException.apply(input, e);
    }
}
