package io.hanbings.carbon.controller;

import io.hanbings.carbon.command.StopCommand;
import io.hanbings.carbon.interfaces.CacheService;
import io.hanbings.carbon.interfaces.ConsoleService;
import io.hanbings.carbon.interfaces.Controller;
import io.hanbings.carbon.interfaces.DatabaseService;
import io.hanbings.carbon.interfaces.TaskService;
import io.hanbings.carbon.interfaces.WebServerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record ConsoleController(TaskService taskService,
                                ConsoleService consoleService,
                                DatabaseService databaseService,
                                WebServerService webServerService,
                                CacheService cacheService) implements Controller {
    @Override
    public void serve() {
        consoleService.start();
        // 注册控制台命令
        consoleService.command("stop", new StopCommand(taskService, consoleService,
                databaseService, webServerService, cacheService));
        // 控制权交给控制台
        consoleService.console();
    }
}
