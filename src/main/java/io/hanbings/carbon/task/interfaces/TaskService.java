package io.hanbings.carbon.task.interfaces;

import io.hanbings.carbon.interfaces.Service;

public interface TaskService extends Service {
    void thread(Runnable runnable);
}
