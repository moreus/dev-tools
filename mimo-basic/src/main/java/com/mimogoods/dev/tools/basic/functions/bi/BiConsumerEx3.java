package com.mimogoods.dev.tools.basic.functions.bi;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BiConsumerEx3 {

    public static void main(String[] args) {
        BiConsumer<String, String> printer = BiConsumerEx3::printContactInfo;
        Map<String, String> contacts = new HashMap<>();
        contacts.put("John", "12345");
        contacts.put("Bill", "34567");
        contacts.put("Jack", "22563");

        contacts.forEach(printer);

    }

    public static void printContactInfo(String name, String phoneNumber) {
        System.out.println(name + "--" + phoneNumber);
    }
}
