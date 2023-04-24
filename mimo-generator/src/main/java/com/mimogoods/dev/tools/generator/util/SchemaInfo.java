package com.mimogoods.dev.tools.generator.util;


import com.mimogoods.dev.tools.generator.core.Section;

import java.util.HashMap;
import java.util.Map;

public class SchemaInfo {
    public Section section;

    public String entityName;
    public String parentEntityName;
    public String parentRelName;
    public Map<String, String> childrenRelNameMap;

    public Map<String, String> columns;
    public Map<String, Map<String, String>> extraColumnFields;

    public SchemaInfo(Section section, String entityName) {
        this.section = section;
        this.entityName = entityName;
        this.childrenRelNameMap = new HashMap<>();
        this.columns = new HashMap<>();
        this.extraColumnFields = new HashMap<>();
    }

    public void addExtraField(String columnName, String fieldName, String fieldValue) {
        if (extraColumnFields.get(columnName) == null) {
            extraColumnFields.put(columnName, new HashMap<>());
        }
        extraColumnFields.get(columnName).put(fieldName, fieldValue);
    }
}