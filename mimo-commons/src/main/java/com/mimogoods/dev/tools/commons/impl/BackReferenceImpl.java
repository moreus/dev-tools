package com.mimogoods.dev.tools.commons.impl;

import com.mimogoods.dev.tools.commons.definition.BackReference;

import java.util.HashMap;
import java.util.Map;

public class BackReferenceImpl<C, P> implements BackReference<C, P> {

    private final Map<C, P> references = new HashMap<>();
    @Override
    public P getParent(C child) {
        return references.get(child);
    }

    @Override
    public void addParent(C child, P parent) {
        references.put(child, parent);
    }
}

