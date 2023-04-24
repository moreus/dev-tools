package com.mimogoods.dev.tools.generator.core;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Field {

    public static String TRUE = "TRUE";
    public static String FALSE = "false";

    private String name;
    private String type;
    private String derivedUsing;
    private Derived derived;
    private Mapping mapping;
    private List<Validation> validations;
    private List<Action> actions;
    private Merge merge;

    // variables used for database format
    private String length;
    private String precision;
    private String scale;
    private String persistable;
    private String defaultValue;
    private String isUnique;
    private String mandatory;
    @ToString.Exclude
    private Section parent;

    public boolean hasActions() {
        return Objects.nonNull(actions) && !actions.isEmpty();
    }

    public boolean hasValidations() {
        return Objects.nonNull(validations) && !validations.isEmpty();
    }

    public boolean isNotPersistable() {
        return getPersistable() != null && getPersistable().equalsIgnoreCase(FALSE);
    }

    public boolean isUnique() {
        return getIsUnique() != null && getIsUnique().equals("true");
    }

    public boolean isRequired() {
        final boolean isRequired = TRUE.equalsIgnoreCase(mandatory);
        return isRequired || StringUtils.isNotBlank(defaultValue);
    }

    public boolean hasXMLMapping() {
        return mapping != null && mapping.getXml() != null && StringUtils.isNotBlank(mapping.getXml().getLocalName());
    }
}
