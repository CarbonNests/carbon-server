package io.hanbings.carbon.router;

import io.hanbings.carbon.common.content.Message;
import io.hanbings.carbon.service.RepositionMessageService;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class ProtectRoute implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext event) {
        event.response().putHeader("content-type", "application/json; charset=utf-8");
        event.response().end(RepositionMessageService
                .buildMessage("405", Message.Status.NOT_ALLOW_OR_NOT_SUPPORT));
    }
}
