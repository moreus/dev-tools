package com.mimogoods.dev.tools.commons.definition;

import com.mimogoods.dev.tools.commons.dto.ApiMapping;
import com.mimogoods.dev.tools.commons.dto.EventMapping;

import java.util.List;

public interface MappingDefinition {

    String getJson();
    Integer getLineNumber();
    List<? extends ApiMapping> getApis();
    List<? extends EventMapping> getEventMappings();
}
