package com.mimogoods.dev.tools.generator.util;

import com.mimogoods.dev.tools.generator.core.Section;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class YAMLReader {
    protected Set<String> processedYAML = new HashSet<>();
    protected Map<String, Section> baseSectionMap = new HashMap<>();

    public Map<String, Section> getBaseSectionMap() {
        return baseSectionMap;
    }
}
