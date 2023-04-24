package com.mimogoods.dev.tools.generator.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutboundApi {
    private String name;
    private String endpoint;
    private Endpoint endpoints;
    private String method;
    private String requestTemplate;
    private boolean isForRouting;
}
