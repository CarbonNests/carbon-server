package io.hanbings.carbon.router;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public record OAuth2ServiceHandler() implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext event) {

    }
}
