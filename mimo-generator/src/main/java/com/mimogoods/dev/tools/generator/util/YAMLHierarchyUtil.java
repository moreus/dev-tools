package com.mimogoods.dev.tools.generator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

public class YAMLHierarchyUtil {
    private static final Logger Log = LoggerFactory.getLogger(YAMLHierarchyUtil.class);
    private static Map<String, Integer> countMap;
    private static Set<String> visitedYamlSet;
    private static Map<String, Set<String>> hierarchyMap;
    private static Set<String> definitionYamlSet;
    private static YAMLReader yamlReader;

    public static YAMLReader getYamlReader() {
        return yamlReader;
    }
}
