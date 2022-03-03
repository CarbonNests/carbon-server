package io.hanbings.carbon.controller;

import io.hanbings.carbon.interfaces.Controller;
import io.hanbings.carbon.interfaces.TaskService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record TaskController(TaskService taskService) implements Controller {
    @Override
    public void serve() {
        taskService.start();
    }
}
