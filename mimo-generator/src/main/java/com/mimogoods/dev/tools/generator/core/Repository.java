package com.mimogoods.dev.tools.generator.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Repository {

    private String name;
    private String expression;
    private String type;
    private String returnType;
    private SearchCriteria searchCriteria;
}