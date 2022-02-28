package io.hanbings.carbon.router;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class ProtectRoute implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext event) {
        event.response().putHeader("content-type", "application/json; charset=utf-8");
        event.response().end();
    }
}
