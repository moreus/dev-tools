package com.mimogoods.dev.tools.setp;

import java.util.function.Predicate;

public class ConditionalStep<T> extends DependableStep<T>  {
    private final Predicate<T> predicate;
    private final DependableStep<T> failureStep;

    protected ConditionalStep(String name, Predicate<T> predicate, DependableStep<T> failureStep) {
        super(name);
        this.predicate = predicate;
        this.failureStep = failureStep;
    }

    public DependableStep<T> getNext(T t) {
        return predicate.test(t) ? failureStep.getRoot() : getNext();
    }
}
