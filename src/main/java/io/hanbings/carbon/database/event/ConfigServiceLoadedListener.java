package io.hanbings.carbon.database.event;

import io.hanbings.carbon.config.event.ConfigServiceLoadedEvent;
import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.database.MongoDBService;
import io.hanbings.carbon.event.interfaces.EventHandler;
import io.hanbings.carbon.event.interfaces.Listener;

public class ConfigServiceLoadedListener implements Listener {
    @EventHandler
    public void onConfigServiceLoaded(ConfigServiceLoadedEvent event) {
        ServiceContainer container = event.getServiceContainer();
        container.setDatabaseService(new MongoDBService(container));
        container.getDatabaseService().start();
        container.getEventBus().callEvent(new DatabaseServiceLoadedEvent(container));
    }
}
