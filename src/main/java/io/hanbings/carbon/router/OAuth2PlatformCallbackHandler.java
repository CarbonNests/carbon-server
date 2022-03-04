package io.hanbings.carbon.router;

import io.hanbings.carbon.interfaces.CacheService;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public record OAuth2PlatformCallbackHandler(CacheService cacheService) implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext event) {
        event.response().end(event.request().params().toString());
    }
}
