package com.mimogoods.dev.tools.generator.core;

import com.mimogoods.dev.tools.commons.dto.EventDefinition;
import com.mimogoods.dev.tools.stream.StreamUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
public class Egress {
    private List<OutboundApi> apis;
    private List<EventDefinition> events;

    public Stream<OutboundApi> getAPIStream() {
        return StreamUtils.getStream(apis);
    }

    public Stream<EventDefinition> getEventStream() {
        return StreamUtils.getStream(events);
    }

}
