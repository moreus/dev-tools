package com.mimogoods.dev.tools.commons.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDefinition {
    private String name;
    private String when;
    private String eventClass;
    private TopicName topicName;
}