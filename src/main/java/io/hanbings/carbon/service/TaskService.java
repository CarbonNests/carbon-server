package io.hanbings.carbon.service;

import io.hanbings.carbon.container.ServerConfig;
import io.hanbings.carbon.service.interfaces.Service;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskService implements Service {
    @Setter
    @Getter
    ThreadPoolExecutor threadPool;
    ServerConfig config;

    private TaskService(ServerConfig config) {
        this.config = config;
    }

    public static TaskService create(ServerConfig config) {
        return new TaskService(config);
    }

    @Override
    public void init() {
        this.threadPool = new ThreadPoolExecutor(
                config.getThreadCorePoolSize(),
                config.getThreadMaxPoolSize(),
                config.getThreadKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
        );
    }

    @Override
    public void close() {
        this.threadPool.shutdown();
    }
}
