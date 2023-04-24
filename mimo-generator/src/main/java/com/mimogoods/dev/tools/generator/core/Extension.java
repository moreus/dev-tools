package com.mimogoods.dev.tools.generator.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Extension {
    private String baseSection;
    private String extensionPrefix;
    private String basePackage;
    private String discriminator;
}

