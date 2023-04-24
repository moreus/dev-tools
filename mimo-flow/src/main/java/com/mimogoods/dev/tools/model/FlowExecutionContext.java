package com.mimogoods.dev.tools.model;

import com.mimogoods.dev.tools.setp.DependableStep;

import java.util.Optional;

import static org.immutables.value.Value.*;

@Immutable
public interface FlowExecutionContext<T> {
    T getValue();
    Optional<DependableStep<T>> getStep();
    Optional<Throwable> getThrowable();
    //re-throw when no exception handlers matched
    @Default
    default boolean reThrow() {
        return false;
    }

    default DependableStep<T> getRequiredStep() {
        return getStep().orElseThrow(IllegalStateException::new) ;
    }

    static <T> FlowExecutionContext<T> of(T value, DependableStep<T> step) {
        return ImmutableFlowExecutionContext.<T>builder()
                .value(value)
                .step(step)
                .build();
    }

}
