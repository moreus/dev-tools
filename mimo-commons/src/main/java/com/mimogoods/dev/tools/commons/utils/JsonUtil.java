package com.mimogoods.dev.tools.commons.utils;


public class JsonUtil {
    private JsonUtil(){}
    public static final String JSON_WILDCARD_ARRAY = "[*]";
    public static final int JSON_WILDCARD_ARRAY_LENGTH = 3;
    public static final String JSON_ROOT_PREFIX = "$.";
    public static final String JSON_ROOT = "$";

    public static String getTerminalPath(String jsonPath) {
        return jsonPath.substring(jsonPath.lastIndexOf(MetadataCommonsConstants.PATH_SEPARATOR) + 1);
    }

    public static String getCollectionField(String jsonPath) {
        return jsonPath
                .substring(
                        jsonPath.lastIndexOf(JSON_WILDCARD_ARRAY) +
                                JSON_WILDCARD_ARRAY_LENGTH + 1);
    }


}

