package io.hanbings.carbon.task;

import io.hanbings.carbon.container.ConfigContainer;
import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.task.interfaces.TaskService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadService implements TaskService {
    ServiceContainer container;
    ThreadPoolExecutor threadPool;

    public ThreadService(ServiceContainer container) {
        this.container = container;
    }

    @Override
    public void start() {
        ConfigContainer config = container.getConfigService().getConfigContainer();
        this.threadPool = new ThreadPoolExecutor(
                config.getThreadCorePoolSize(),
                config.getThreadMaxPoolSize(),
                config.getThreadKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
        );
        log.info("task service started.");
    }

    @Override
    public void stop() {
        threadPool.shutdown();
        log.info("task service stopped.");
    }

    @Override
    public void thread(Runnable runnable) {
        threadPool.execute(runnable);
    }
}
