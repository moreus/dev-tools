package com.mimogoods.dev.tools.generator.core;


import com.mimogoods.dev.tools.commons.dto.ApiMapping;
import com.mimogoods.dev.tools.commons.dto.EventMapping;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Mapping {

    private String cXML;
    private String json;
    private String object;
    private Excel excel;
    private Database database;
    private DomainModel domainModel;
    private XML xml;
    private List<ApiMapping> apis;
    private List<EventMapping> events;

}