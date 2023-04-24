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
public class Api {

    public final static String HTTP_PROTOCOL = "http";
    public final static String WEBSOCKET_PROTOCOL = "websocket";

    private String name;
    private String protocol;
    private boolean singleResult;
    private String returnType;
    private List<Param> params;
    private String persistenceService;

    public boolean isHTTPProtocol() {
        return HTTP_PROTOCOL.equals(protocol);
    }

    public boolean isWebosocketProtocol() {
        return WEBSOCKET_PROTOCOL.equals(protocol);
    }
}
