package com.mimogoods.dev.tools.parser;

import lombok.Data;

@Data
public class FlowRelation {
    private Flow left;
    private Flow right;
    private FlowOperator operator; //->, (, )
}
