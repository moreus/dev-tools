package com.mimogoods.dev.tools.generator.impl;

import com.mimogoods.dev.tools.generator.MetadataClassNames;
import com.mimogoods.dev.tools.generator.context.GeneratorContext;
import com.mimogoods.dev.tools.generator.core.Field;
import com.mimogoods.dev.tools.generator.core.Section;
import com.squareup.javapoet.ClassName;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Optional;
import java.util.Stack;

@Getter
@Setter
public class CoreGeneratorContext {
    // core generator shared context
    private final GeneratorContext generatorContext;
    protected String yamlVersion;
    protected String excelPojoPackageName;
    protected String metadataPackageName;
    protected String modelPackageName;
    protected String immutablePackageName;
    protected String opticsPackageName;
    protected String validationsPackageName;
    protected String deserializerPackageName;
    protected String overridesPackageName;
    protected String springPackageName;
    protected String filtersPackageName;
    protected String directQueryPackageName;
    protected String lookupPackageName;
    protected String entityPackageName;
    protected String repositoryPackageName;
    protected MetadataClassNames metadataClassNames;
    protected ClassName entityMapperClassName;
    protected ClassName springConfigClassName;

    // traversing context
    private boolean isBaseSection; // set to true when going through section from base yaml files
    private Section rootSection;
    private Stack<Section> sectionStack;
    private Field field; // current field
    private boolean isBaseField; // indicates if field is from base yaml; i.e. from baseDocumentReference.yaml


    public CoreGeneratorContext(GeneratorContext generatorContext) {
        this.generatorContext = generatorContext;
    }

    public Section getSection(final String sectionName) {
        return getSection(sectionName, rootSection);
    }

    private Section getSection(final String sectionName, final Section section) {
        if (section.getName().equals(sectionName)) {
            return section;
        }
        if (section.hasChildren()) {
            Optional<Section> sectionPresent = section.getChildren().stream().map(child -> getSection(sectionName, child)).filter(Objects::nonNull).findFirst();
            if (sectionPresent.isPresent()) {
                return sectionPresent.get();
            }
        }
        return null;
    }
}
