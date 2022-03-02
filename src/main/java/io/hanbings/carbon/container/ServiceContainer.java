package io.hanbings.carbon.container;

import io.hanbings.carbon.config.interfaces.ConfigService;
import io.hanbings.carbon.console.interfaces.ConsoleService;
import io.hanbings.carbon.database.interfaces.DatabaseService;
import io.hanbings.carbon.event.EventBus;
import io.hanbings.carbon.task.interfaces.TaskService;
import io.hanbings.carbon.web.interfaces.WebServerService;
import lombok.Data;

@Data
public class ServiceContainer {

    EventBus eventBus;
    ConfigService configService;
    ConsoleService consoleService;
    DatabaseService databaseService;
    TaskService taskService;
    WebServerService webServerService;

    private ServiceContainer(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public static ServiceContainer registry(EventBus eventBus) {
        return new ServiceContainer(eventBus);
    }
}
