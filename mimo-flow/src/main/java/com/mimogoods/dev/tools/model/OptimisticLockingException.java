package com.mimogoods.dev.tools.model;

import java.math.BigDecimal;

public class OptimisticLockingException extends TransientException
{
    private final String entityName;
    private final BigDecimal id;
    private final Integer rowVersion;

    public OptimisticLockingException(String entityName, BigDecimal id, Integer rowVersion)
    {
        this.entityName = entityName;
        this.id = id;
        this.rowVersion = rowVersion;
    }

    @Override
    public String getMessage() {
        return "OptimisticLockingException for entity: " + entityName + " id: " + id + " rowVersion: " + rowVersion;
    }
}