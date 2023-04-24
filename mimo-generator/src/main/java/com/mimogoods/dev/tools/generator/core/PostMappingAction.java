package com.mimogoods.dev.tools.generator.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostMappingAction {
    private String action;
    private String input;
    private String inputType;
    private String inputConverter;
    private boolean excelPostMappingAction;
}
