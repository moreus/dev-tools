package com.mimogoods.dev.tools.generator.core;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Excel {
    public static final String TRUE = "TRUE";

    private String columnName;
    private Integer rank;
    private String converter;
    /**
     * this flag overrides the mandatory flag in Field for excel mapping
     */
    private String mandatory;
    /**
     * shows where the fields is copied from to get the excel column name
     */
    private String copyFrom;

    public Boolean isRequired() {
        return mandatory == null ? null : TRUE.equalsIgnoreCase(mandatory);
    }
}
