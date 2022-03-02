package io.hanbings.carbon.web.event;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class WebServerServiceLoadedEvent extends Event {
    @Getter
    ServiceContainer serviceContainer;
}
