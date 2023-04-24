package com.mimogoods.dev.tools.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Util {
    private Util(){}
    public static String getCollectionName (final String sectionName) {
        if (sectionName.endsWith("y")) {
            return sectionName.substring(0, sectionName.length() - 1) + MetadataCommonsConstants.IES_COLLECTION_SUFFIX;
        }
        return sectionName + MetadataCommonsConstants.S_COLLECTION_SUFFIX;
    }

    public static <T> List<T> add(List<T> list, T[] elements) {
        if (Objects.isNull(list)) {
            list = new ArrayList<>();
        }
        list.addAll(Arrays.asList(elements));
        return list;
    }


    public static String convertToCSV(String... values) {
        return Arrays.stream(values)
                .map(Optional::ofNullable)
                .map(s -> s.orElse(StringUtils.EMPTY))
                .map(Util::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (escapedData.contains("\"")) {
            escapedData = escapedData.replace("\"", "\"\"");
        }
        escapedData = "\"" + escapedData + "\"";
        return escapedData;
    }

    public static String substringBetweenIncluding(final String xmlString, final String open, final String close) {
        return open + StringUtils.substringBetween(xmlString, open, close) + close;
    }
}
