package io.hanbings.carbon.controller;

import io.hanbings.carbon.controller.interfaces.Controller;
import io.hanbings.carbon.data.ServerConfig;
import io.hanbings.carbon.service.VertxService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RouterController implements Controller {
    ServerConfig config;
    TaskController taskController;
    VertxService server;

    public RouterController(ServerConfig config, TaskController taskController) {
        this.config = config;
        this.taskController = taskController;
    }

    @Override
    public void run() {
        log.info("RouterController run.");
        this.server = new VertxService(config.getServerPort());
        this.taskController.task(new Thread(() -> { server.run(); }));
    }

    @Override
    public void stop() {
        log.info("RouterController stop.");
    }
}
