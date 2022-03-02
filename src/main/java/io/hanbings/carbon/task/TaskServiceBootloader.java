package io.hanbings.carbon.task;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.interfaces.ServiceBootloader;
import io.hanbings.carbon.task.event.DatabaseServiceLoadedListener;
import io.hanbings.carbon.task.event.TaskServiceLoadedEvent;

public class TaskServiceBootloader implements ServiceBootloader {
    @Override
    public void load(ServiceContainer container) {

    }

    @Override
    public void events(ServiceContainer container) {
        container.getEventBus().registerEvent(new TaskServiceLoadedEvent(container));
    }

    @Override
    public void listeners(ServiceContainer container) {
        container.getEventBus().registerListener(new DatabaseServiceLoadedListener());
    }
}
