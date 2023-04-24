package com.mimogoods.dev.tools.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiMapping {
    private String name;
    private String requestPath;
    private String responsePath;
    private String path;
    private String key;
    private Class type;
    private boolean isFromContext;
}
