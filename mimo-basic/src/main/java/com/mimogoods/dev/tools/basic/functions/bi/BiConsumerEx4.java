package com.mimogoods.dev.tools.basic.functions.bi;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 非静态方法使用
 */
public class BiConsumerEx4 {
    public static void main(String[] args) {
        Map<String, String> contacts = new HashMap<>();
        contacts.put("John", "123456");
        contacts.put("Bill", "22345");
        contacts.put("caigen", "33567");

        CardTemplate template = new CardTemplate("designed by Bill");
        BiConsumer<String, String> bc = template::printCard;
        contacts.forEach(bc);
    }
}

class CardTemplate {
    private String someInfo;

    public CardTemplate(String someInfo) {
        this.someInfo = someInfo;
    }

    public void printCard(String phone, String name) {
        System.out.println("----------");
        System.out.println("Full name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println(someInfo);
        System.out.println();
    }
}