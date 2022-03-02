package io.hanbings.carbon.console.event;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ConsoleServiceLoadedEvent extends Event {
    @Getter
    ServiceContainer serviceContainer;
}
