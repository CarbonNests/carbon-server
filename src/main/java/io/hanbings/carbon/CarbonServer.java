package io.hanbings.carbon;

import io.hanbings.carbon.common.content.Carbon;
import io.hanbings.carbon.container.ServerConfig;
import io.hanbings.carbon.container.ServerController;
import io.hanbings.carbon.container.ServerDependencyContainer;
import io.hanbings.carbon.container.ServerService;
import io.hanbings.carbon.controller.CarbonController;
import io.hanbings.carbon.controller.DatabaseController;
import io.hanbings.carbon.controller.RouterController;
import io.hanbings.carbon.controller.TaskController;
import io.hanbings.carbon.service.MongoDBService;
import io.hanbings.carbon.service.ResponseMessageService;
import io.hanbings.carbon.service.ThreadService;
import io.hanbings.carbon.service.VertxService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarbonServer {
    public static void main(String[] args) {
        log.info(Carbon.LOGO);
        // 注入依赖容器
        log.info("load controller.");
        ServerDependencyContainer container = new ServerDependencyContainer();
        ServerService services = new ServerService();
        ServerController controller = new ServerController();

        // 加载配置文件
        log.info("initialize config.");
        ServerConfig config = ConfigReader.loadConfig();
        container.setConfig(config);

        // 初始化服务
        log.info("load service.");
        services.setThreadService(ThreadService.create(config));
        services.setVertxService(VertxService.create(config));
        services.setMongoDBService(MongoDBService.create(config));
        services.setResponseMessageService(ResponseMessageService.create());

        // 初始化控制器
        log.info("load controller.");
        controller.setTaskController(TaskController.create(services));
        controller.setRouterController(RouterController.create(services));
        controller.setDatabaseController(DatabaseController.create(services));
        controller.setCarbonController(CarbonController.create(container));

        // 移交权限
        log.info("initialize dependency.");
        container.setService(services);
        container.setController(controller);
        container.getController().getCarbonController().start();
    }
}
