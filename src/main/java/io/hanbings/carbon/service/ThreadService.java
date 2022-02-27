package io.hanbings.carbon.service;

import io.hanbings.carbon.service.interfaces.RunnableService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadService implements RunnableService {
    ThreadPoolExecutor threadPool;
    int corePoolSize;
    int maximumPoolSize;
    long keepAliveTime;
    TimeUnit unit;
    BlockingQueue<java.lang.Runnable> queue;

    private ThreadService() {}

    /**
     * 这个构造方法有点多余来着
     * 先放着
     *
     * @param threadPool 线程池
     */
    public ThreadService(ThreadPoolExecutor threadPool) {
        this.threadPool = threadPool;
    }

    /**
     * 设置一个线程池
     *
     * @param corePoolSize    线程池的基本大小
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime   保持存活时间
     * @param unit            线程池维护线程所允许的空闲时间的单位
     * @param queue           线程池所使用的缓冲队列
     */
    public ThreadService(int corePoolSize,
                         int maximumPoolSize,
                         long keepAliveTime,
                         TimeUnit unit,
                         BlockingQueue<java.lang.Runnable> queue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.queue = queue;
    }

    /**
     * 获取线程池
     *
     * @return 线程池
     */
    public ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

    /**
     * 将线程加入线程池
     *
     * @param thread 线程实例
     */
    public void execute(Thread thread) {
        this.threadPool.execute(thread);
    }

    @Override
    public void run() {
        this.threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                unit, queue);
    }

    @Override
    public void stop() {
        this.threadPool.shutdown();
    }
}
