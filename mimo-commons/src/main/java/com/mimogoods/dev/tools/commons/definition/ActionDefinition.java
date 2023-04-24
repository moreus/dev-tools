package com.mimogoods.dev.tools.commons.definition;

import java.util.function.Function;

public interface ActionDefinition {

    Function getAction();
    Function getConditions();
}
