package com.mimogoods.dev.tools.setp;

import java.util.Objects;
import java.util.Optional;

public abstract class DependableStep<T> extends Step<T> {
    private DependableStep<T> previous;
    private DependableStep<T> next;
    protected DependableStep(String name) {
        super(name);
    }

    public synchronized DependableStep<T> dependsOn(DependableStep<T> provider) {
        if (Objects.nonNull(this.previous)) {
            throw new IllegalArgumentException(String.format(
                    "Multiple dependents not supported yet. %s step cannot depend on %s because %s already depends on" +
                            " %s",
                    provider.getName(), this.getName(), this.getName(), this.previous.getName()));
        }
        if (Objects.nonNull(provider.next)) {
            throw new IllegalArgumentException(String.format(
                    "Multiple providers not supported yet. %s step cannot depend on %s because %s already provides " +
                            "for %s",
                    provider.getName(), this.getName(), provider.getName(), provider.next.getName()));
        }
        this.previous = provider;
        provider.next = this;
        return this;
    }

    public Optional<DependableStep<T>> getPrevious() {
        return Optional.ofNullable(previous);
    }

    public DependableStep<T> getNext(T t) {
        return getNext();
    }

    public DependableStep<T> getNext() {
        return next;
    }

    public DependableStep<T> getRoot() {
        return getPrevious().map(DependableStep::getRoot).orElse(this);
    }

}
