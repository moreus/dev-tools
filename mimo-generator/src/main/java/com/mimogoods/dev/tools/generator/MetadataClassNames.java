package com.mimogoods.dev.tools.generator;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetadataClassNames implements CodeGenerator {
    public final static String BASE_REPOSITORY_PREFIX = "_";
    private final static String ELEMENT_CLASS_NAME = "Element";
    private final static String CONDITION_CLASS_NAME = "Condition";
    private final static String PERSISTENCE_SERVICE_SUFFIX = "PersistenceService";
    private final static String REPOSITORY_SUFFIX = "Repository";
    private final static String REPOSITORY_IMPL_SUFFIX = "RepositoryImpl";
    private final static String REPOSITORY_SPECIALIZED_SUFFIX = "RepositorySpecialized";
    private final static String SEARCH_CRITERIA_SUFFIX = "SearchCriteria";
    private final static String REST_API_SUFFIX = "RestController";
    private final static String WEBSOCKET_API_SUFFIX = "WebSocketController";

    private final String basePackageName;
    private ClassName elementClassName;
    private ClassName mappingClassName;
    private ClassName fieldClassName;
    private ClassName conditionClassName;
    private TypeName listOfConditions;
    private ClassName actionClassName;
    private TypeName listOfActions;
    private ClassName validationClassName;
    private TypeName listOfValidations;
    private ClassName customActionBaseClassName;
    private ClassName resultEvaluatorBaseClassName;
    private ClassName objectDefinitionClassName;
    private ClassName transactionalObjectClassName;
    private ClassName mapperInterfaceName;
    private ClassName mapperLookupClassName;
    private ClassName baseTargetMapperClassName;
    private ClassName mapperSourceInterfaceName;
    private ClassName mapperSourceServiceClassName;
    private ClassName outboundAPIClassName;
    private ClassName uniqueActionClassName;
    private ClassName filteringClassName;
    private ClassName functionClassName;
    private ClassName biFunctionClassName;

    public MetadataClassNames(String commonPackageName) {
        this(null, commonPackageName);
    }

    public MetadataClassNames(String basePackageName, String commonPackageName) {
        this.basePackageName = basePackageName;
        elementClassName = ClassName.get(commonPackageName + COMMON_PACKAGE_NAME, ELEMENT_CLASS_NAME);
        mappingClassName = ClassName.get(commonPackageName + COMMON_PACKAGE_NAME, MAPPING_CLASS_NAME);
        fieldClassName = ClassName.get(commonPackageName + COMMON_PACKAGE_NAME, FIELD_CLASS_NAME);
        conditionClassName = ClassName.get(commonPackageName + COMMON_PACKAGE_NAME, CONDITION_CLASS_NAME);
        actionClassName = ClassName.get(commonPackageName + COMMON_PACKAGE_NAME, ACTION_CLASS_NAME);
        validationClassName = ClassName.get(commonPackageName + COMMON_PACKAGE_NAME, VALIDATION_CLASS_NAME);
        customActionBaseClassName = ClassName.get(commonPackageName + CUSTOM_ACTIONS_PACKAGE_NAME, CUSTOM_ACTION_BASE_CLASS_NAME);
        resultEvaluatorBaseClassName = ClassName.get(commonPackageName + CUSTOM_VALIDATIONS_PACKAGE_NAME, RESULT_EVALUATOR_BASE_CLASS_NAME);
        objectDefinitionClassName = ClassName.get(commonPackageName + COMMON_PACKAGE_NAME, OBJECT_DEFINITION_CLASS_NAME);
        transactionalObjectClassName = ClassName.get(commonPackageName + COMMON_PACKAGE_NAME, TRANSACTIONAL_OBJECT_CLASS_NAME);
        mapperInterfaceName = ClassName.get(commonPackageName + COMMON_PACKAGE_NAME, MAPPER_INTERFACE_NAME);
        outboundAPIClassName = ClassName.get(commonPackageName + COMMON_PACKAGE_NAME, OUTBOUND_API_CLASS_NAME);
        uniqueActionClassName = ClassName.get(commonPackageName + COMMON_PACKAGE_NAME, UNIQUE_ACTION_CLASS_NAME);
        filteringClassName = ClassName.get(commonPackageName + COMMON_PACKAGE_NAME, FILTERING_CLASS_NAME);
        functionClassName = ClassName.get("java.util.function", "Function");
        biFunctionClassName = ClassName.get("java.util.function", "BiFunction");

        mapperLookupClassName = ClassName.get(MAPPER_SPRING_PACKAGE_NAME, MAPPER_LOOKUP_CLASS_NAME);
        baseTargetMapperClassName = ClassName.get(MAPPER_MODEL_PACKAGE_NAME, BASE_TARGET_MAPPER_CLASS_NAME);
        mapperSourceInterfaceName = ClassName.get(MAPPER_MODEL_PACKAGE_NAME, MAPPER_SOURCE_INTERFACE_NAME);
        mapperSourceServiceClassName = ClassName.get(MAPPER_SPRING_PACKAGE_NAME, MAPPER_SOURCE_SERVICE_CLASS_NAME);
        conditionClassName = ClassName.get(COMMON_PACKAGE_NAME, CONDITION_CLASS_NAME);

        listOfValidations = ParameterizedTypeName.get(list, validationClassName);
        listOfActions = ParameterizedTypeName.get(list, actionClassName);
        listOfConditions = ParameterizedTypeName.get(list, conditionClassName);
    }
}
