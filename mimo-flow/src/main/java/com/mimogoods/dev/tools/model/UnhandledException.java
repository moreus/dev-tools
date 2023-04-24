package com.mimogoods.dev.tools.model;

import lombok.Getter;

@Getter
public class UnhandledException extends RuntimeException {
    private final Object value;

    public UnhandledException(final Throwable e, final Object value) {
        super(e);
        this.value = value;
    }

}
