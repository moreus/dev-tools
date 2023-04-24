package com.mimogoods.dev.tools.generator.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DomainModel {
    private String targetObject;
    private String targetField;
    private String targetType;
    private String targetFieldMandatory;
}

