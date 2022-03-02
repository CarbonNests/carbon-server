package io.hanbings.carbon.web;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.web.interfaces.WebServerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServerService implements WebServerService {
    ServiceContainer container;

    public HttpServerService(ServiceContainer container) {
        this.container = container;
    }

    @Override
    public void start() {
        log.info("web server service started.");
    }

    @Override
    public void stop() {
        log.info("web server service stopped.");
    }
}
