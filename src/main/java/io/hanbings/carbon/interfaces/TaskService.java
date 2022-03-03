package io.hanbings.carbon.interfaces;

public interface TaskService extends Service {
    void thread(Runnable runnable);
}
