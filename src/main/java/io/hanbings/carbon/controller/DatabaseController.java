package io.hanbings.carbon.controller;

import io.hanbings.carbon.interfaces.Controller;
import io.hanbings.carbon.interfaces.DatabaseService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record DatabaseController(DatabaseService databaseService) implements Controller {
    @Override
    public void serve() {
        // databaseService.start();
    }
}
