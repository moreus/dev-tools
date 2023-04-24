package com.mimogoods.dev.tools.commons.definition;


public interface APIDefinition {
    String getName();

    String getEndpoint();
    String getSupplierEndpoint();
    String getBuyerEndpoint();

    String getMethod();

    String getRequestTemplate();
    boolean isForRouting();
}

