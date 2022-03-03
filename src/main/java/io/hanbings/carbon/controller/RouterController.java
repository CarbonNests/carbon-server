package io.hanbings.carbon.controller;

import io.hanbings.carbon.interfaces.ConsoleService;
import io.hanbings.carbon.interfaces.Controller;
import io.hanbings.carbon.interfaces.DatabaseService;
import io.hanbings.carbon.interfaces.TaskService;
import io.hanbings.carbon.interfaces.WebServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record RouterController(TaskService taskService,
                               ConsoleService consoleService,
                               DatabaseService databaseService,
                               WebServerService webServerService) implements Controller {
    @Override
    public void serve() {
        webServerService.start();
    }
}
