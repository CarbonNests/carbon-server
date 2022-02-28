package io.hanbings.carbon.router;

import io.hanbings.carbon.container.ServerConfig;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OAuth2CallbackRoute implements Handler<RoutingContext> {
    ServerConfig config;

    public OAuth2CallbackRoute(ServerConfig config) {
        this.config = config;
    }

    @Override
    public void handle(RoutingContext event) {
        log.info(event.request().body().toString());
        event.response().end(event.request().body().toString());
    }
}
