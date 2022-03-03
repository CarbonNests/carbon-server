package io.hanbings.carbon.router;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class OAuth2CallbackHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext event) {
        event.response().end(event.request().params().toString());
    }
}
