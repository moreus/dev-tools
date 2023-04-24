package com.mimogoods.dev.tools.generator.core;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UniqueAction {

    String strategy;
    String field;
    String tableName;
    List<String> uniquenessColumns;
    String message;
    String messageComment;
    String messageCode;
    String messageIfEmpty;
    String messageIfEmptyComment;
    String messageIfEmptyCode;
}