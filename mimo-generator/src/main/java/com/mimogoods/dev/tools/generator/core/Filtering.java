package com.mimogoods.dev.tools.generator.core;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Filtering {

    String strategy;
    List<String> params;
    UniqueAction uniqueAction;
}
