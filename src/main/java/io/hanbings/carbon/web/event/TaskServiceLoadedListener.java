package io.hanbings.carbon.web.event;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.event.interfaces.EventHandler;
import io.hanbings.carbon.event.interfaces.Listener;
import io.hanbings.carbon.task.event.TaskServiceLoadedEvent;
import io.hanbings.carbon.web.HttpServerService;

public class TaskServiceLoadedListener implements Listener {
    @EventHandler
    public void on(TaskServiceLoadedEvent event) {
        ServiceContainer container = event.getServiceContainer();
        container.setWebServerService(new HttpServerService(container));
        container.getWebServerService().start();
        container.getEventBus().callEvent(new WebServerServiceLoadedEvent(container));
    }
}
