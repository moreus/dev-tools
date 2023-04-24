package com.mimogoods.dev.tools.generator.util;

import com.mimogoods.dev.tools.generator.core.Field;
import com.mimogoods.dev.tools.generator.core.Section;

import java.util.Map;
import java.util.Stack;

public interface InfoCollector<T> {
    public boolean collectInfoFromSection(Section section, Section parentSection, Map<String, T> infoMap, Stack<Section> sectionStack, Stack<String> sectionPathStack);

    public void collectInfoForField(Field field, Map<String, T> infoMap, Section parentSection, Section lastCollectedSection, String sectionPath);
}
