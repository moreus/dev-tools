package com.mimogoods.dev.tools.generator;

import com.mimogoods.dev.tools.generator.context.GeneratorContext;
import com.mimogoods.dev.tools.generator.core.Field;
import com.mimogoods.dev.tools.generator.core.Section;
import com.mimogoods.dev.tools.generator.impl.CoreGeneratorContext;

public interface Generator {
    void setup(GeneratorContext generatorContext, CoreGeneratorContext coreGeneratorContext);

    void preProcess(Section root);

    void init();

    boolean shouldVisitBaseSection();

    boolean shouldVisitBaseFields();

    void visit(Section section);

    void beforeVisitChildrenSection(Section section); // method call before recursion

    void afterVisitChildrenSection(Section section);

    void visitEnd(Section section);

    void visit(Field field);

    void visitYamlEnd(final String sourceName);

    void visitAllYamlEnd();
}
