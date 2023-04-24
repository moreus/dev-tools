package com.mimogoods.dev.tools.generator.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.mimogoods.dev.tools.generator.core.Field.TRUE;

@Getter
@Setter
@ToString
public class Database {
    private String tableName;
    private String columnName;
    private String relationshipName;
    /**
     * parent to child relationship type (default is one-to-many)
     */
    private String relationshipType;
    private String targetTableName;
    private String outerRelationshipName;
    /**
     * existingObject lookup by field
     */
    private String lookupField;
    /**
     * existingObject lookup by field
     */
    private String compositeLookup;
    /**
     * existingObject lookup rule; can be either "required" or "optional"
     */
    private String lookupRule;
    private String uniqueIndex;

    private String hasPostMapping;

    public boolean hasPostMapping() {
        return TRUE.equalsIgnoreCase(hasPostMapping);
    }
}
