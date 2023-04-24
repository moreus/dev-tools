package com.mimogoods.dev.tools.generator.util;

import com.mimogoods.dev.tools.generator.core.Field;
import com.mimogoods.dev.tools.generator.core.Section;

public class InfoCollectorUtil {
    public final static String REL_TYPE_MANY_TO_ONE = "many-to-one";
    public final static String LOOKUP_RULE_OPTIONAL = "optional";
    public final static String LOOKUP_RULE_REQUIRED = "required";

    public static String getSectionNameFullNameWithOptional(Section section) {
        return section.isRequired() ? section.getFullName() : section.getFullName() + "?";
    }

    public static String getEntityNameFromSection(Section section) {
        if (section.getMapping() != null && section.getMapping().getDatabase() != null) {
            return section.getMapping().getDatabase().getTableName();
        }
        return null;
    }

    public static String getEntityRelNameFromSection(Section section) {
        if (section.getMapping() != null && section.getMapping().getDatabase() != null) {
            return section.getMapping().getDatabase().getRelationshipName();
        }
        return null;
    }

    public static String getEntityOuterRelNameFromSection(Section section) {
        if (section.getMapping() != null && section.getMapping().getDatabase() != null) {
            return section.getMapping().getDatabase().getOuterRelationshipName();
        }
        return null;
    }

    public static String getTableNameFromField(Field field) {
        if (field.getMapping() != null && field.getMapping().getDatabase() != null &&
                field.getMapping().getDatabase().getTableName() != null) {
            return field.getMapping().getDatabase().getTableName();
        }
        return null;
    }

    public static String getColumnNameFromField(Field field) {
        if (field.getMapping() != null && field.getMapping().getDatabase() != null &&
                field.getMapping().getDatabase().getColumnName() != null) {
            return field.getMapping().getDatabase().getColumnName();
        }
        return null;
    }
}
