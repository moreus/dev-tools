package com.mimogoods.dev.tools.generator.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersistenceService {

    private String name;
    private boolean singleResult;
    private String returnType;
    private List<MatchExpression> matchExpressions;
}
