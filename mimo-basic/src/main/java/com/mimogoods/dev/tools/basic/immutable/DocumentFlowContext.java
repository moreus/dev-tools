package com.mimogoods.dev.tools.basic.immutable;

import org.immutables.value.Value;

@Value.Immutable
public interface DocumentFlowContext<T extends DomainModel> extends WithDocumentFlowContext<T>{
    @Value.Default
    default boolean shouldSkipSaveDocument() {
        return false;
    }

    @Value.Default
    default boolean isAsync() {
        return false;
    }

    Class<T> getModelClass();
}
