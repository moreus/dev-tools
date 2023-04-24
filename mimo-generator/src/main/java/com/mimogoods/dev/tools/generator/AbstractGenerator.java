package com.mimogoods.dev.tools.generator;

import com.mimogoods.dev.tools.generator.context.GeneratorContext;
import com.mimogoods.dev.tools.generator.core.Field;
import com.mimogoods.dev.tools.generator.core.Section;
import com.mimogoods.dev.tools.generator.impl.CoreGeneratorContext;

public abstract class AbstractGenerator implements Generator {

    public static boolean isCXMLSection(final Section section) {
        return section.hasXMLMapping() && section.getMapping().getXml().getLocalName().equals("cXML");
    }

    public static boolean isCustomDeserializer(final Section section) {
        return section.getFieldStream()
                .filter(Field::hasXMLMapping)
                .filter(field -> !field.getMapping().getXml().getLocalName().equals("."))
                .anyMatch(field -> field.getMapping().getXml().getLocalName().contains("."));
    }

    abstract public void setup(final GeneratorContext generatorContext, final CoreGeneratorContext coreGeneratorContext);

    abstract public boolean shouldVisitBaseSection();

    @Override
    public boolean shouldVisitBaseFields() {
        return shouldVisitBaseSection();
    }

    @Override
    public void preProcess(Section root) {

    }

    @Override
    public void init() {
    }

    @Override
    public void visit(final Section section) {
    }

    @Override
    public void beforeVisitChildrenSection(final Section section) {
    }

    @Override
    public void afterVisitChildrenSection(final Section section) {
    }

    @Override
    public void visitEnd(final Section section) {
    }

    @Override
    public void visit(final Field field) {
    }

    @Override
    public void visitYamlEnd(final String sourceName) {
    }

    @Override
    public void visitAllYamlEnd() {
    }
}
