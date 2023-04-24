package com.mimogoods.dev.tools.basic.generic;

import java.util.Collections;
import java.util.List;

public class GenericBasic1 {
    public <T extends Interface1 & Interface2> T getValue() {
        return (T) new Impl12();
    }
}
