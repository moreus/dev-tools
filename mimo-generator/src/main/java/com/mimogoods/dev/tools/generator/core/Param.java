package com.mimogoods.dev.tools.generator.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Param {

    private String name;
    private String paramType;
    private String value;
}