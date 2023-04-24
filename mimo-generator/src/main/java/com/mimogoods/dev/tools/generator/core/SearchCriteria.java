package com.mimogoods.dev.tools.generator.core;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {

    private String name;
    private List<Field> fields;
    private String sortCol;
    private String sortDir;
    private String total;

    public boolean hasFields() {
        return fields != null && !fields.isEmpty();
    }

}
