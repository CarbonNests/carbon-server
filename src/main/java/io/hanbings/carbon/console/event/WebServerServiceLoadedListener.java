package io.hanbings.carbon.console.event;

import io.hanbings.carbon.console.JLineService;
import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.event.interfaces.EventHandler;
import io.hanbings.carbon.event.interfaces.Listener;
import io.hanbings.carbon.web.event.WebServerServiceLoadedEvent;

public class WebServerServiceLoadedListener implements Listener {
    @EventHandler
    public void onWebServerServiceLoaded(WebServerServiceLoadedEvent event) {
        ServiceContainer container = event.getServiceContainer();
        container.setConsoleService(new JLineService(container));
        container.getConsoleService().start();
        container.getEventBus().callEvent(new ConsoleServiceLoadedEvent(container));
    }
}
