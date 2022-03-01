package io.hanbings.carbon.container;

import io.hanbings.carbon.config.interfaces.ConfigService;
import io.hanbings.carbon.console.interfaces.ConsoleService;
import io.hanbings.carbon.database.interfaces.DatabaseService;
import io.hanbings.carbon.task.interfaces.TaskService;
import io.hanbings.carbon.web.interfaces.WebServerService;
import lombok.Data;

@Data
public class ServiceContainer {
    ConfigService config;
    ConsoleService console;
    DatabaseService database;
    TaskService task;
    WebServerService webserver;
}
