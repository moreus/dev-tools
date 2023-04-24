package com.mimogoods.dev.tools.generator;

import com.mimogoods.dev.tools.generator.core.Section;
import com.mimogoods.dev.tools.generator.util.CodeGeneratorUtil;
import com.squareup.javapoet.ClassName;

public class PersistenceServiceGenerator implements CodeGenerator {

    private final CodeGeneratorUtil codeGeneratorUtil;
    private final MetadataClassNames metadataClassNames;

    public PersistenceServiceGenerator(CodeGeneratorUtil codeGeneratorUtil, MetadataClassNames metadataClassNames) {
        this.codeGeneratorUtil = codeGeneratorUtil;
        this.metadataClassNames = metadataClassNames;
    }

    public void generate(Section section, ClassName sectionClassName) {
        if (section.hasPersistenceServices()) {
            //JDO code removed, implement JPA services here
        }
    }

}