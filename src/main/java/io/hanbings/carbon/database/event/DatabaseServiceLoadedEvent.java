package io.hanbings.carbon.database.event;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DatabaseServiceLoadedEvent extends Event {
    @Getter
    ServiceContainer serviceContainer;
}
