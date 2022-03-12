package io.hanbings.carbon.router;

import io.hanbings.carbon.common.content.Fun;
import io.hanbings.carbon.common.content.Message;
import io.hanbings.carbon.common.util.MessageUtils;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class HaveFunRandomHandler implements Handler<RoutingContext> {

  @Override
  public void handle(RoutingContext event) {
    event.response().putHeader("content-type", "application/json charset=utf-8");
    event.response().setStatusCode(200)
            .end(MessageUtils.json("200", Message.Status.SUCCESS, "fun", Fun.fun()));
  }
}
