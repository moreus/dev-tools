package com.mimogoods.dev.tools.setp;

import java.util.function.Function;

public class SimpleStep<T> extends DependableStep<T> {
    private final Function<T,T> function;
    protected SimpleStep(String name, Function<T, T> function) {
        super(name);
        this.function = function;
    }

    public T execute(T input) {
        return function.apply(input);
    }
}
