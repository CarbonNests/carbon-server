package io.hanbings.carbon;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CarbonServerBootstrapEvent extends Event {
    @Getter
    ServiceContainer serviceContainer;
}
