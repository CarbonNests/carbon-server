package io.hanbings.carbon.command;

import io.hanbings.carbon.interfaces.CacheService;
import io.hanbings.carbon.interfaces.Command;
import io.hanbings.carbon.interfaces.ConsoleService;
import io.hanbings.carbon.interfaces.DatabaseService;
import io.hanbings.carbon.interfaces.TaskService;
import io.hanbings.carbon.interfaces.WebServerService;

public record StopCommand(TaskService taskService,
                          ConsoleService consoleService,
                          DatabaseService databaseService,
                          WebServerService webServerService,
                          CacheService cacheService) implements Command {

    @Override
    public void command(String[] args) {
        webServerService.stop();
        databaseService.stop();
        taskService.stop();
        cacheService.stop();
        consoleService.stop();
    }
}
