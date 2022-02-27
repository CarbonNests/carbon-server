package io.hanbings.carbon.controller;

import io.hanbings.carbon.common.content.Carbon;
import io.hanbings.carbon.controller.interfaces.Controller;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarbonController implements Controller {

    public CarbonController() {
        log.info(Carbon.LOGO);
    }

    @Override
    public void run() {
        log.info("CarbonController run.");
        ConfigController configController = new ConfigController();
        configController.run();
        TaskController taskController = new TaskController(configController.getServerConfig());
        taskController.run();
        RouterController routerController = new RouterController(configController.getServerConfig(), taskController);
        routerController.run();
        CommandController commandController =
                new CommandController(this, configController, taskController, routerController);
        commandController.run();
    }

    @Override
    public void stop() {
        log.info("CarbonController stop.");
    }
}
