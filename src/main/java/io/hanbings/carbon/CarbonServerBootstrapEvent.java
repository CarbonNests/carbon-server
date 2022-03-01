package io.hanbings.carbon;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.event.Event;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarbonServerBootstrapEvent extends Event {
    @Setter
    @Getter
    ServiceContainer container;

    public CarbonServerBootstrapEvent(ServiceContainer container) {
        this.container = container;
    }
}
