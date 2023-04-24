package com.mimogoods.dev.tools.generator.core;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Action extends Operation {

    private String expression;
    private List<Condition> conditions;

}

