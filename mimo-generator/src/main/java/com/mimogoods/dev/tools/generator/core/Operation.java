package com.mimogoods.dev.tools.generator.core;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Operation {
    private String name;
    private String type;
    private String implementation;
    private List<String> params;
}
