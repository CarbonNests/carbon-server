package io.hanbings.carbon.controller;

import io.hanbings.carbon.container.ServerService;
import io.hanbings.carbon.controller.interfaces.Controller;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskController implements Controller {
    ServerService services;

    private TaskController(ServerService services) {
        this.services = services;
    }

    public static TaskController create(ServerService services) {
        return new TaskController(services);
    }

    @Override
    public void start() {
        log.info("initialize thread pool.");
        services.getTaskService().init();
    }

    @Override
    public void stop() {
        log.info("shopping thread pool.");
        services.getTaskService().close();
    }
}
