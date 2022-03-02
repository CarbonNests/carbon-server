package io.hanbings.carbon.web;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.interfaces.ServiceBootloader;
import io.hanbings.carbon.web.event.TaskServiceLoadedListener;
import io.hanbings.carbon.web.event.WebServerServiceLoadedEvent;

public class WebServerServiceBootloader implements ServiceBootloader {
    @Override
    public void load(ServiceContainer container) {

    }

    @Override
    public void events(ServiceContainer container) {
        container.getEventBus().registerEvent(new WebServerServiceLoadedEvent(container));
    }

    @Override
    public void listeners(ServiceContainer container) {
        container.getEventBus().registerListener(new TaskServiceLoadedListener());
    }
}
