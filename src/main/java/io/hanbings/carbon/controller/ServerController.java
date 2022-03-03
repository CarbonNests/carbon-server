package io.hanbings.carbon.controller;

import io.hanbings.carbon.config.Config;
import io.hanbings.carbon.interfaces.ConsoleService;
import io.hanbings.carbon.interfaces.Controller;
import io.hanbings.carbon.interfaces.DatabaseService;
import io.hanbings.carbon.interfaces.TaskService;
import io.hanbings.carbon.interfaces.WebServerService;
import io.hanbings.carbon.service.JLineService;
import io.hanbings.carbon.service.MongodbService;
import io.hanbings.carbon.service.ThreadService;
import io.hanbings.carbon.service.VertxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ServerController implements Controller {
    final Config config;
    TaskService taskService;
    ConsoleService consoleService;
    DatabaseService databaseService;
    WebServerService webServerService;

    public void serve() {
        // 创建服务
        taskService = new ThreadService(config.getThreadCorePoolSize()
                , config.getThreadMaxPoolSize()
                , config.getThreadKeepAliveTime());
        databaseService = new MongodbService(config.getMongodbUrl()
                , config.getMongodbUser()
                , config.getMongodbPassword());
        webServerService = new VertxService(config.getServerPort(), taskService);
        consoleService = new JLineService();

        // 创建控制器
        new TaskController(taskService).serve();
        new DatabaseController(databaseService).serve();
        new RouterController(taskService, consoleService, databaseService, webServerService).serve();
        new ConsoleController(taskService, consoleService, databaseService, webServerService).serve();
    }
}
