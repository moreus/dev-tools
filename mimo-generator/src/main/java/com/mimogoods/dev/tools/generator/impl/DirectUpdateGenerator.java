package com.mimogoods.dev.tools.generator.impl;

import com.mimogoods.dev.tools.generator.AbstractGenerator;
import com.mimogoods.dev.tools.generator.context.GeneratorContext;
import com.mimogoods.dev.tools.generator.core.Field;
import com.mimogoods.dev.tools.generator.core.Section;
import com.mimogoods.dev.tools.generator.util.CodeGeneratorUtil;

import java.util.Map;

public class DirectUpdateGenerator extends AbstractGenerator {
    public static final String OLD_VALUE_PREFIX = "previous";

    // generator variables
    protected CodeGeneratorUtil codeGeneratorUtil;
    protected CoreGeneratorContext coreGeneratorContext;
    protected Map<String, Section> baseSectionMap;

    @Override
    public void setup(final GeneratorContext generatorContext, final CoreGeneratorContext coreGeneratorContext) {
        this.codeGeneratorUtil = new CodeGeneratorUtil(generatorContext.getGenerationOut(), coreGeneratorContext.metadataPackageName);
        this.coreGeneratorContext = coreGeneratorContext;
    }

    @Override
    public void preProcess(Section root) {

    }

    @Override
    public boolean shouldVisitBaseSection() {
        return false;
    }

    @Override
    public void visit(Section section) {

    }

    @Override
    public void beforeVisitChildrenSection(Section section) {

    }

    @Override
    public void afterVisitChildrenSection(Section section) {

    }

    @Override
    public void visitEnd(Section section) {

    }

    @Override
    public void visit(Field field) {

    }
}
