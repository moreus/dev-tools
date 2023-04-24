package com.mimogoods.dev.tools.core;

import com.mimogoods.dev.tools.model.FlowExecutionContext;
import com.mimogoods.dev.tools.model.ImmutableFlowExecutionContext;
import com.mimogoods.dev.tools.model.TransientException;
import com.mimogoods.dev.tools.model.UnhandledException;
import com.mimogoods.dev.tools.setp.DependableStep;
import com.mimogoods.dev.tools.setp.Step;
import com.mimogoods.dev.tools.stream.StreamUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public class Flow<T>{
    private final T                                                  input;
    private final DependableStep<T>                                  outputStep;
    private final BiFunction<Supplier<T>, String, T> stepExecutor;
    private final DependableStep<T>                                  finalStep;
    private final Map<Class<? extends Throwable>, DependableStep<T>> exceptionSteps;
    private final int                                                maxRetries;
    private       int                                                currentRetries;
    public static <T> FlowBuilder<T> builder() {
        return new FlowBuilder<>();
    }

    private static <T> ImmutableFlowExecutionContext<T>
    reThrowException(final Throwable e, final T t)
    {
        return ImmutableFlowExecutionContext.<T>builder()
                .reThrow(true)
                .value(t)
                .step(Step.<T>identity().build())
                .throwable(e)
                .build();
    }

    public T run() {
        final FlowExecutionContext<T> result = runSteps(getInitialContext());
        final FlowExecutionContext<T> finalResult = getFinalStep()
                .map(s -> runSteps(ImmutableFlowExecutionContext.<T>builder().from(result).step(s).build()))
                .orElse(result);
        if (finalResult.reThrow()) {
            throw new UnhandledException(finalResult.getThrowable().orElseThrow(IllegalStateException::new),
                    finalResult.getValue());
        }
        return finalResult.getValue();
    }

    private FlowExecutionContext<T> getInitialContext() {
        return FlowExecutionContext.of(input, outputStep.getRoot());
    }

    private FlowExecutionContext<T> runSteps(final FlowExecutionContext<T> executionContext) {
        return StreamUtils
                .takeWhile(Stream.iterate(executionContext,
                                this::next),
                        context -> context.getStep().isPresent())
                .reduce((a, b) -> b)
                .orElseThrow(IllegalStateException::new);
    }

    private FlowExecutionContext<T> next(FlowExecutionContext<T> previous) {
        final DependableStep<T> step = previous.getRequiredStep();
        try {
            T next = wrap(executeStep(previous), step.getName());
            return ImmutableFlowExecutionContext.<T>builder()
                    .from(previous)
                    .value(next)
                    .step(Optional.ofNullable(step.getNext(next)))
                    .build();
        } catch (Throwable e) {
            if (previous.getThrowable().isPresent()) {
                //allow inner flow to re-throw exception to outer flows
                return reThrowException(e, previous.getValue());
            }
            try {
                return handleException(e, previous.getValue());
            } catch (Throwable ea) {
                //handle exception in exception handling
                return reThrowException(ea, previous.getValue());
            }
        }
    }

    private Supplier<T> executeStep(final FlowExecutionContext<T> context) {
        return context.getThrowable()
                .<Supplier<T>>map(t -> () -> context.getRequiredStep().execute(context.getValue(), t))
                .orElseGet(() -> () -> context.getRequiredStep().execute(context.getValue()));
    }

    private FlowExecutionContext<T> handleException(Throwable e, T t) {
        if (e instanceof TransientException && currentRetries < maxRetries) {
            currentRetries++;
            return getInitialContext();
        }
        return exceptionSteps.entrySet().stream()
                .filter(x -> x.getKey().isInstance(e))
                .map(Map.Entry::getValue)
                .findFirst()
                .map(s -> ImmutableFlowExecutionContext.<T>builder()
                        .step(s.getRoot())
                        .value(t)
                        .throwable(e)
                        .build())
                .orElseGet(() -> reThrowException(e, t));
    }

    private T wrap(final Supplier<T> toExecute, final String name) {
        return getStepExecutor().map(e -> e.apply(toExecute, name))
                .orElseGet(toExecute);
    }

    public Optional<BiFunction<Supplier<T>, String, T>> getStepExecutor() {
        return Optional.ofNullable(stepExecutor);
    }

    public Optional<DependableStep<T>> getFinalStep() {
        return Optional.ofNullable(finalStep);
    }

    public static final class FlowBuilder<T> {
        private final Map<Class<? extends Throwable>, DependableStep<T>> exceptionSteps = new LinkedHashMap<>();
        private       T                                                  input;
        private       DependableStep<T>                                  outputStep;
        private       BiFunction<Supplier<T>, String, T>                 stepExecutor;
        private       DependableStep<T>                                  finalStep;
        private       int                                                maxRetries;

        private FlowBuilder() {
        }

        public FlowBuilder<T> withInput(T input) {
            this.input = input;
            return this;
        }

        public FlowBuilder<T> withOutputStep(DependableStep<T> outputStep) {
            this.outputStep = Step.<T>identity().dependsOn(outputStep);
            return this;
        }

        public FlowBuilder<T> withStepExecutor(BiFunction<Supplier<T>, String, T> stepExecutor) {
            this.stepExecutor = stepExecutor;
            return this;
        }

        public FlowBuilder<T> on(Class<? extends Throwable> exception, DependableStep<T> exceptionStep) {
            this.exceptionSteps.put(exception, Step.<T>identity().dependsOn(exceptionStep));
            return this;
        }

        public FlowBuilder<T> maxRetriesOnTransientException(int maxRetries) {
            this.maxRetries = maxRetries;
            return this;
        }

        public FlowBuilder<T> doFinally(DependableStep<T> finalStep) {
            this.finalStep = finalStep;
            return this;
        }

        public Flow<T> build() {
            return new Flow<>(input, outputStep, stepExecutor, finalStep, exceptionSteps, maxRetries);
        }
    }
}
