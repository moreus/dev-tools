package com.mimogoods.dev.tools.basic.immutable;

import org.immutables.value.Value;

/**
 * @author I068378
 */
@Value.Immutable(singleton = true, builder = false)
public interface MultipleStaticMethodInInterface {
    static String getName(){
        return "";
    }

    static String getUser(){
        return "";
    }
}
