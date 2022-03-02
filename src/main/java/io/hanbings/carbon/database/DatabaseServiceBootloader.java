package io.hanbings.carbon.database;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.database.event.ConfigServiceLoadedListener;
import io.hanbings.carbon.database.event.DatabaseServiceLoadedEvent;
import io.hanbings.carbon.interfaces.ServiceBootloader;

public class DatabaseServiceBootloader implements ServiceBootloader {
    @Override
    public void load(ServiceContainer container) {

    }

    @Override
    public void events(ServiceContainer container) {
        container.getEventBus().registerEvent(new DatabaseServiceLoadedEvent(container));
    }

    @Override
    public void listeners(ServiceContainer container) {
        container.getEventBus().registerListener(new ConfigServiceLoadedListener());
    }
}
