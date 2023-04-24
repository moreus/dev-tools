package com.mimogoods.dev.tools.commons.definition;

public interface BackReference<C,P> {
    P getParent(C child);
    void addParent(C child, P parent);
}

