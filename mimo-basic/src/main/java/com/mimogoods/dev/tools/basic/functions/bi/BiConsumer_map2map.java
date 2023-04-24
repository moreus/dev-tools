package com.mimogoods.dev.tools.basic.functions.bi;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BiConsumer_map2map {
    public static void main(String[] args) {
        Map<String, Integer> empSalaryMap = new HashMap<>();

        empSalaryMap.put("E01", 100);
        empSalaryMap.put("E02", 200);
        empSalaryMap.put("E03", 300);

        Map<String, Integer> newEmpSalaryMap = empSalaryMap.entrySet()
                .stream()
                .collect(Collectors.toMap(stringIntegerEntry -> stringIntegerEntry.getKey(), stringIntegerEntry -> 2 * stringIntegerEntry.getValue()));

        System.out.println("Origin map: " + empSalaryMap);
        System.out.println();
        System.out.println("New Map: " + newEmpSalaryMap);
    }
}
