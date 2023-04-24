package com.mimogoods.dev.tools.generator.core;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Condition extends Operation {

    private List<Condition> children;

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

}