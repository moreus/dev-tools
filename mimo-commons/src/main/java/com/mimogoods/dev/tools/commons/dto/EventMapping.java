package com.mimogoods.dev.tools.commons.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventMapping {
    private String name;
    private String eventPath;
    private String isLookupKey;
    //these are derived fields, no need to define in yaml
    private String sourceLookupKey;
    private String targetLookupKey;
    private String sourcePath;
}
