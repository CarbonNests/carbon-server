package io.hanbings.carbon.config.event;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ConfigServiceLoadedEvent extends Event {
    @Getter
    ServiceContainer serviceContainer;
}
