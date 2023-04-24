package com.mimogoods.dev.tools.setp;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Step<T> {
    public static final String IDENTITY = "Step.identity";
    private final       String name;
    protected Step(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public T execute(T input, Throwable e) {
        return execute(input);
    }

    public T execute(T input) {
        return input;
    }

    public static class StepBuilder {
        public interface AddDependent<T> {
            DependableStep<T> dependsOn(DependableStep<T> provider);
        }

        public interface SimpleStepAddDependent<T> extends AddDependent<T> {
            SimpleStep<T> build();
        }

        public interface ConditionalStepAddDependent<T> extends AddDependent<T> {
            ConditionalStep<T> build();
        }
    }

    private static abstract class DependableStepBuilder<T> {

        protected final String name;

        private DependableStepBuilder(String name) {
            this.name = name;
        }

        public DependableStep<T> dependsOn(DependableStep<T> provider) {
            return build().dependsOn(provider);
        }

        abstract DependableStep<T> build();
    }

    private static final class SimpleStepBuilder<T> extends DependableStepBuilder<T>
            implements StepBuilder.SimpleStepAddDependent<T>
    {
        private final Function<T, T> function;

        public SimpleStepBuilder(String name, Function<T, T> function) {
            super(name);
            this.function = function;
        }

        @Override
        public SimpleStep<T> build() {
            return new SimpleStep<>(name, function);
        }
    }

    public static <T> StepBuilder.SimpleStepAddDependent<T> with(Function<T, T> function, String name) {
        return new SimpleStepBuilder<>(name, function);
    }

    public static <T> StepBuilder.SimpleStepAddDependent<T> identity() {
        return Step.with(Function.identity(), IDENTITY);
    }

    public static <T> ExceptionHandlerStep<T> of(BiFunction<T, Throwable, T> functionWithException, String name) {
        return new ExceptionHandlerStep<>(name, functionWithException);
    }

    public static <T> StepBuilder.ConditionalStepAddDependent<T> when(
            Predicate<T> predicate,
            DependableStep<T> failureStep,
            String name)
    {
        return new ConditionalStepBuilder<>(name, predicate, Step.<T>identity().dependsOn(failureStep));
    }

    public static <T> StepBuilder.ConditionalStepAddDependent<T> endWhen(Predicate<T> predicate, String name) {
        return new ConditionalStepBuilder<>(name, predicate, Step.<T>identity().build());
    }

    private static final class ConditionalStepBuilder<T> extends DependableStepBuilder<T>
            implements StepBuilder.ConditionalStepAddDependent<T>
    {
        private final Predicate<T> predicate;
        private final DependableStep<T> failureStep;

        public ConditionalStepBuilder(String name, Predicate<T> predicate, DependableStep<T> failureStep) {
            super(name);
            this.predicate = predicate;
            this.failureStep = failureStep;
        }

        @Override
        public ConditionalStep<T> build() {
            return new ConditionalStep<>(name, predicate, Step.<T>identity().dependsOn(failureStep));
        }
    }
}
