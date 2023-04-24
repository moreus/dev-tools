package com.mimogoods.dev.tools.generator.context;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
@Setter
public class GeneratorContext {
    private final String yamlVersion;
    private final String domain;
    private final String xmlBodySection;
    private final String metadataSourceRoot;
    private final String basePackageName;
    private final String generationOut;
    private final String stringsFileOut;
    private final String baseCoreClassesPackageName;
    private final String contextMetaDataFile;
    private final String persistencePackageName;
    private final String entityMapperPackageName;
    private final String sqlFilePrefix;
    private final String idlFileOutputName;
    private final String schemaGenerationOut;
    private final Map<String, Pair<String, String>> errorMessages = new HashMap<>();
    private final String schemaCardinality;
    private final boolean skipActiveColumnGeneration;
    private final Map<String, String> typeAliases;
    private final boolean generateRepositories;
}
