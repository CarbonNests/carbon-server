package io.hanbings.carbon.controller;

import io.hanbings.carbon.interfaces.CacheService;
import io.hanbings.carbon.interfaces.Controller;
import io.hanbings.carbon.interfaces.TaskService;

public record CacheController(CacheService cacheService, TaskService taskService) implements Controller {

    @Override
    public void serve() {
        cacheService.start();
    }
}
