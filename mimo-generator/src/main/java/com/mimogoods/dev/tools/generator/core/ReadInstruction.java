package com.mimogoods.dev.tools.generator.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.mimogoods.dev.tools.generator.core.Field.TRUE;

@Getter
@Setter
@ToString
public class ReadInstruction {
    private String action;
    private String name;
    private String type;
    private String mandatory;
    private Excel excel;

    public boolean isRequired() {
        return TRUE.equalsIgnoreCase(mandatory);
    }
}
