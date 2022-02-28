package io.hanbings.carbon.controller;

import io.hanbings.carbon.container.ServerConfig;
import io.hanbings.carbon.controller.interfaces.Controller;
import io.hanbings.carbon.service.ThreadService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TaskController implements Controller {
    ThreadService threadService;
    ServerConfig config;

    public TaskController(ServerConfig config) {
        this.config = config;
    }

    @Override
    public void run() {
        log.info("TaskController run.");
        log.info("Creating thread pool." +
                "#1 core thread size {} " +
                "#2 max thread size {} " +
                "#3 thread alive time {}",
                config.getThreadCorePoolSize(),
                config.getThreadMaxPoolSize(),
                config.getThreadKeepAliveTime());
        this.threadService = new ThreadService(
                config.getThreadCorePoolSize(),
                config.getThreadMaxPoolSize(),
                config.getThreadKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );
        this.threadService.run();
    }

    @Override
    public void stop() {
        log.info("TaskController stop.");
        this.threadService.stop();
    }

    public void task(Thread task) {
        threadService.execute(task);
    }
}
