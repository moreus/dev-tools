package com.mimogoods.dev.tools.generator.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class Metadata {

    private String name;
    private List<Section> children;
}
