package io.hanbings.carbon.controller;

import io.hanbings.carbon.controller.interfaces.Controller;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarbonController implements Controller {

    public CarbonController() { }

    @Override
    public void run() {
        log.info("CarbonController run.");
        ConfigController configController = new ConfigController();
        RouterController routerController = new RouterController();
        CommandController commandController = new CommandController(this, configController, routerController);
    }

    @Override
    public void stop() {
        log.info("CarbonController stop.");
    }
}
