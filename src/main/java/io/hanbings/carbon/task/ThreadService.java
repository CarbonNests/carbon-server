package io.hanbings.carbon.task;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.task.interfaces.TaskService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadService implements TaskService {
    ServiceContainer container;

    public ThreadService(ServiceContainer container) {
        this.container = container;
    }

    @Override
    public void start() {
        log.info("task service started.");
    }

    @Override
    public void stop() {
        log.info("task service stopped.");
    }

    @Override
    public void thread(Runnable runnable) {

    }
}
