package io.hanbings.carbon.task.event;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TaskServiceLoadedEvent extends Event {
    @Getter
    ServiceContainer serviceContainer;
}