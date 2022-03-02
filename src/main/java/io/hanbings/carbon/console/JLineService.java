package io.hanbings.carbon.console;

import io.hanbings.carbon.console.interfaces.ConsoleService;
import io.hanbings.carbon.container.ServiceContainer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JLineService implements ConsoleService {
    ServiceContainer container;

    public JLineService(ServiceContainer container) {
        this.container = container;
    }

    @Override
    public void console() {

    }

    @Override
    public void start() {
        log.info("console service started.");
    }

    @Override
    public void stop() {
        log.info("console service stopped.");
    }
}
