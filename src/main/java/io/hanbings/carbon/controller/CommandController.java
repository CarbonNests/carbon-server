package io.hanbings.carbon.controller;

import io.hanbings.carbon.controller.interfaces.Controller;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CommandController implements Controller {
    List<Controller> controllers = new ArrayList<>();

    public CommandController(Controller ... controllers) {
        this.controllers.addAll(List.of(controllers));
    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {
        log.info("Stopping all controller.");
        this.controllers.forEach(Controller::stop);
    }
}
