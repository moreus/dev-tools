package com.mimogoods.dev.tools.generator;

import com.squareup.javapoet.ClassName;

public interface CodeGenerator {
    String COMMON_PACKAGE_NAME = ".common";
    String PERSISTENCE_PACKAGE_NAME = ".persistence";
    String REPOSITORY_PACKAGE_NAME = ".repository";
    String REPOSITORY_SPECIALIZED_PACKAGE_NAME = ".repository.specialized";
    String REPOSITORY_SEARCH_CRITERIA_PACKAGE_NAME = REPOSITORY_SPECIALIZED_PACKAGE_NAME + ".searchCriteria";
    String API_PACKAGE_NAME = ".api";
    String REST_API_PACKAGE_NAME = API_PACKAGE_NAME + ".rest";
    String WEBSOCKET_API_PACKAGE_NAME = API_PACKAGE_NAME + ".websocket";
    String MAPPING_PACKAGE_NAME = ".mapping";
    String MAPPER_MODEL_PACKAGE_NAME = "com.sap.ariba.network.cloudstack.commons.metadata.mapper.model";
    String MAPPER_SPRING_PACKAGE_NAME = "com.sap.ariba.network.cloudstack.commons.metadata.mapper.spring";
    String OBJECT_DEFINITION_CLASS_NAME = "ObjectDefinition";
    String TRANSACTIONAL_OBJECT_CLASS_NAME = "TransactionalObject";
    String MAPPER_INTERFACE_NAME = "Mapper";
    String MAPPER_LOOKUP_CLASS_NAME = "MapperLookup";
    String BASE_TARGET_MAPPER_CLASS_NAME = "BaseTargetMapper";
    String MAPPER_SOURCE_INTERFACE_NAME = "MapperSource";
    String MAPPER_SOURCE_SERVICE_CLASS_NAME = "MapperSourceService";
    String OUTBOUND_API_CLASS_NAME = "OutboundAPI";
    String UNIQUE_ACTION_CLASS_NAME = "UniqueAction";
    String FILTERING_CLASS_NAME = "Filtering";
    String FIELD_CLASS_NAME = "Field";
    String MAPPING_CLASS_NAME = "Mapping";
    String MAP_TYPE = "Map";
    String PATH_SEPARATOR = "/";
    String GETTER_PREFIX = "get";
    String BOOLEAN_GETTER_PREFIX = "is";

    String BUILD_METHOD_NAME = "build";
    String BUILD_FIELD_METHOD_NAME = "buildField";
    String BUILD_CHILDREN_METHOD_NAME = "buildChildren";
    String ACTION_CLASS_NAME = "Action";
    String VALIDATION_CLASS_NAME = "Validation";
    String CUSTOM_PACKAGE_NAME = ".custom";
    String CUSTOM_ACTIONS_PACKAGE_NAME = CUSTOM_PACKAGE_NAME + ".action.base";
    String CUSTOM_VALIDATIONS_PACKAGE_NAME = CUSTOM_PACKAGE_NAME + ".validation.base";
    String RESULT_EVALUATOR_BASE_CLASS_NAME = "ResultEvaluatorBase";
    String CUSTOM_ACTION_BASE_CLASS_NAME = "CustomActionBase";
    String HANDLE_METHOD_NAME = "handle";
    String RESULT_PARAM_NAME = "result";
    String ELEMENT_PARAM_NAME = "element";
    String VALIDATION_PARAM_NAME = "validation";
    String SECTION_PARAM_NAME = "section";
    String OBJECT_PARAM_NAME = "object";
    String MERGE_PREFIX = "merge";
    String ACTION_SUFFIX = "Action";
    String EXTENSION_DISCRIMINATOR = "DISCRIMINATOR";
    String DEFAULT_DISCRIMINATOR = "DEFAULT";

    // base class base method names
    String GET_SECTION_NAME_METHOD_NAME = "getSectionName";
    String GET_VERSION_VALUE_METHOD_NAME = "getYamlVersion";
    String GET_JSON_PATH_METHOD_NAME = "getJsonPath";
    String GET_CXML_PATH_METHOD_NAME = "getCxmlPath";
    String GET_OBJECT_PATH_METHOD_NAME = "getObjectPath";

    ClassName list = ClassName.get("java.util", "List");
    ClassName collection = ClassName.get("java.util", "Collection");
    ClassName map = ClassName.get("java.util", "Map");

    String DOCUMENT_DEFINITION_CLASS = "com.sap.ariba.network.cloudstack.commons.metadata.document" +
            ".adaptor.DocumentDefinition";
}
