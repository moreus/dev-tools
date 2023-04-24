package com.mimogoods.dev.tools.generator.core;

import com.mimogoods.dev.tools.generator.impl.CoreGeneratorContext;
import com.mimogoods.dev.tools.generator.impl.DirectUpdateGenerator;
import com.mimogoods.dev.tools.generator.util.InfoCollectorUtil;
import com.mimogoods.dev.tools.generator.util.YAMLHierarchyUtil;
import com.mimogoods.dev.tools.stream.StreamUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.mimogoods.dev.tools.generator.core.Field.TRUE;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Section {
    public static final String DOCUMENT_DEFINITION = "DocumentDefinition";
    private String name;
    private String type; //Collection, Single Object
    private String uniqueKey; //Collection, Single Object
    private String packageName;
    private Mapping mapping;
    private List<PostMappingAction> postMappingAction;
    private List<ReadInstruction> readInstruction;
    private String excelGroupBy;
    //this is not used at the moment, it was added to support map types
    private String key;
    private String value;
    private List<Field> fields;
    @ToString.Exclude
    private Section parent;
    private List<Section> children;
    private List<PersistenceService> persistenceServices;
    private List<Api> apis;
    private List<Validation> validations;
    private Egress egress;
    private List<Repository> repositories;
    private String referenceName;
    private Extension extension;
    private Filtering filtering;
    private boolean generateExtensionElements;
    private String implementClass;
    private String shouldGroup;
    private String mandatory;

    // class name for common manually implemented mojo classes
    private String implementation;
    // model for common manually implemented mojo classes
    private String model;
    private boolean isReference;
    private String derivedUsing;

    public static Stream<Field> getBaseFields(Section section) {
        if (Objects.isNull(section)) {
            return Stream.empty();
        }
        return Stream.concat(section.getFieldStream(), getBaseFields(section.getBaseSection()));
    }

    public static Stream<Section> getAllChildrenStream(Section section) {
        if (Objects.isNull(section)) {
            return Stream.empty();
        }
        return Stream.concat(section.getChildrenStream(), getAllChildrenStream(section.getBaseSection()));
    }

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    public boolean hasFields() {
        return fields != null && !fields.isEmpty();
    }

    public Stream<Field> getFieldStream() {
        return StreamUtils.getStream(fields);
    }

    public Stream<Section> getChildrenStream() {
        return StreamUtils.getStream(children);
    }

    public boolean hasPersistenceServices() {
        return persistenceServices != null && !persistenceServices.isEmpty();
    }

    public boolean hasApis() {
        return apis != null && !apis.isEmpty();
    }

    public boolean hasValidations() {
        return validations != null && !validations.isEmpty();
    }

    public boolean hasRepositories() {
        return repositories != null && !repositories.isEmpty();
    }

    public boolean isGenerateBaseElements() {
        return referenceName != null;
    }

    public boolean hasFixedImplementation() {
        return getImplementation() != null && !getImplementation().isEmpty();
    }

    public boolean isRequired() {
        return TRUE.equalsIgnoreCase(mandatory);
    }

    public boolean isDocumentRoot() {
        final String implementClass = getImplementClass();
        return StringUtils.isNoneBlank(implementClass) && implementClass.endsWith(DOCUMENT_DEFINITION);
    }

    public Section getBaseSection() {
        final Extension extension = getExtension();
        return Objects.nonNull(extension) ?
                YAMLHierarchyUtil.getYamlReader().getBaseSectionMap().get(extension.getBaseSection()) : null;
    }

    public Stream<Field> getAllFieldStream() {
        return getBaseFields(this);
    }

    public Stream<Section> getAllChildrenStream() {
        return getAllChildrenStream(this);
    }

    public Stream<Section> streamTree() {
        return Stream.concat(Stream.of(this), getAllChildrenStream().flatMap(Section::streamTree));
    }

    public boolean hasXMLMapping() {
        return mapping != null && mapping.getXml() != null && StringUtils.isNotBlank(mapping.getXml().getLocalName());
    }

    public Optional<Section> findChildSection(String sectionName) {
        return getAllChildrenStream()
                .filter(Objects::nonNull)
                .filter(s -> s.getFullName().equals(capitalize(sectionName)))
                .findFirst();
    }

    public String getFullName() {
        if (Objects.nonNull(getExtension()) && Objects.nonNull(getExtension().getExtensionPrefix())) {
            return getExtension().getExtensionPrefix().concat(getName());
        }
        return getName();
    }

    public boolean hasPrevious() {
        return getFieldStream().anyMatch(field -> field.getName().startsWith(DirectUpdateGenerator.OLD_VALUE_PREFIX));
    }

    public Field getFieldFromSection(final String fieldName) {
        return getAllFieldStream()
                .filter(field -> field.getName().equalsIgnoreCase(fieldName.trim()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error on Repository Generation: No field has been " +
                        "found for section: " + getName() + " field: " + fieldName));
    }

    public String getEntityFieldOrRelationship(final CoreGeneratorContext coreGeneratorContext, final String fieldName) {
        return getAllFieldStream().filter(field -> field.getName().equalsIgnoreCase(fieldName))
                .map(this::getFieldEntityName)
                .findFirst()
                .orElseGet(getRelationshipName(coreGeneratorContext, fieldName));
    }

    private Supplier<String> getRelationshipName(final CoreGeneratorContext coreGeneratorContext, String fieldName) {
        Section section = coreGeneratorContext.getSection(StringUtils.capitalize(fieldName));
        if (Objects.nonNull(section) && fieldName.equalsIgnoreCase(section.getName())) {
            return () -> InfoCollectorUtil.getEntityRelNameFromSection(this) + "Id";
        }
        return null;
    }

    private String getFieldEntityName(final Field field) {
        String columnName = InfoCollectorUtil.getColumnNameFromField(field);
        return Objects.isNull(columnName) ? field.getName() : columnName;
    }

    public boolean isDerived() {
        return isNotBlank(derivedUsing);
    }
}

