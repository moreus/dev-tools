package com.mimogoods.dev.tools.basic.functions.bi;


/**
 * addThen(BiConsumer after)
 * <p>
 * addThen()方法返回一个关联的BiConsumer，首先，当前BiConsumer被调用，然后after将被调用next，如果在上述两个步骤之一中发生错误，
 * 则将错误，传递给调用者。如果当前BiConsumer出现错误则after会被忽略。
 * // default 修饰符只能用在接口的属性或者方法中并且被修饰的方法必须有实现
 * default BiConsumer<T, U> andThen(BiConsumer<? super T, ? super U> after) {
 * Objects.requireNonNull(after);
 * return (l, r) -> {
 * accept(l, r);
 * after.accept(l, r);
 * };
 * }
 */

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class BiConsumer_addthen_Ex1 {
    public static void main(String[] args) {
        Map<String, Integer> contacts = new HashMap<>();

        Predicate<Integer> predicate = i -> i % 2 == 1;

        contacts.put("John", 123456);
        contacts.put("Bill", 12580);
        contacts.put("Lisa", 16979);

        BiConsumer<String, Integer> biConsumer1 =
                (name, phone) -> System.out.println("Name: " + name + " Number: " + phone);
        BiConsumer<String, Integer> biConsumer2 =
                (name, phone) -> System.out.println("Name: " + name.toUpperCase() + " Number: " + phone);

        contacts.forEach(biConsumer1.andThen(biConsumer2));

    }
}