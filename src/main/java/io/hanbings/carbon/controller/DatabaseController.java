package io.hanbings.carbon.controller;

import io.hanbings.carbon.container.ServerService;
import io.hanbings.carbon.controller.interfaces.Controller;

public class DatabaseController implements Controller {
    ServerService services;

    private DatabaseController(ServerService services) {
        this.services = services;
    }

    public static DatabaseController create(ServerService services) {
        return new DatabaseController(services);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
