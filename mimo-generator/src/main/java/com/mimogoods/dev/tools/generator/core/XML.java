package com.mimogoods.dev.tools.generator.core;

import lombok.Data;

@Data
public class XML {
    private String localName;
    private String useWrapping;
    private String isAttribute;

    public boolean isAttribute() {
        return Boolean.parseBoolean(isAttribute);
    }
}
