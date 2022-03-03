package io.hanbings.carbon.service;

import io.hanbings.carbon.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class ThreadService implements TaskService {

    final int threadCorePoolSize;
    final int threadMaxPoolSize;
    final long threadKeepAliveTime;
    ThreadPoolExecutor threadPool;

    @Override
    public void start() {
        this.threadPool = new ThreadPoolExecutor(
                this.threadCorePoolSize,
                this.threadMaxPoolSize,
                this.threadKeepAliveTime,
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
