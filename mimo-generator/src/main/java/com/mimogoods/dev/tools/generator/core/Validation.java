package com.mimogoods.dev.tools.generator.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@NoArgsConstructor
@Data
public class Validation {

    public static final String PROVIDED_TYPE = "provided";

    private String name;
    private String criteria;
    private String type;
    private String errorCode;
    private String errorMessage;
    private String errorMessageComment;
    private String errorMessageArgs;
    @ToString.Exclude
    private Section baseSection;

    public boolean isProvided() {
        return PROVIDED_TYPE.equals(type);
    }

    public boolean isStandard() {
        return Objects.isNull(type);
    }

}
