package io.hanbings.carbon.controller;

import io.hanbings.carbon.container.ServerService;
import io.hanbings.carbon.controller.interfaces.Controller;

public class RouterController implements Controller {
    ServerService services;

    private RouterController(ServerService service) {
        this.services = service;
    }

    public static RouterController create(ServerService service) {
        return new RouterController(service);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
