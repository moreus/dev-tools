package com.mimogoods.dev.tools.basic.functions.bi;

import java.util.function.BiConsumer;

/**
 * BiConsumer是一个函数式接口，代表一个接受两个输入参数且不返回任何内容的操作符
 *
 * @FunctionalInterface public interface BiConsumer<T, U> {
 * void accept(T t, U u);
 * }
 * default BiConsumer<T, U> andThen(BiConsumer<? super T, ? super U> after);
 * 注：Consumer是一个类似于BiConsumer的接口，但它只接受一个参数
 */
public class BiConsumerEx1 {
    public static void main(String[] args) {
        BiConsumer<String, String> greeter = (firstname, lastname) -> System.out.println("Hello " + firstname + " " + lastname);
        greeter.accept("James", "Smith");
    }
}
