package io.hanbings.carbon.task.event;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.database.event.DatabaseServiceLoadedEvent;
import io.hanbings.carbon.event.interfaces.EventHandler;
import io.hanbings.carbon.event.interfaces.Listener;
import io.hanbings.carbon.task.ThreadService;

public class DatabaseServiceLoadedListener implements Listener {
    @EventHandler
    public void onDatabaseServiceLoaded(DatabaseServiceLoadedEvent event) {
        ServiceContainer container = event.getServiceContainer();
        container.setTaskService(new ThreadService(container));
        container.getTaskService().start();
        container.getEventBus().callEvent(new TaskServiceLoadedEvent(container));
    }
}
