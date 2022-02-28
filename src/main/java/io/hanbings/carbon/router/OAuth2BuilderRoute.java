package io.hanbings.carbon.router;

import io.hanbings.carbon.common.content.Message;
import io.hanbings.carbon.common.util.UuidUtils;
import io.hanbings.carbon.data.OAuth2Platform;
import io.hanbings.carbon.data.ServerConfig;
import io.hanbings.carbon.service.RepositionMessageService;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OAuth2BuilderRoute implements Handler<RoutingContext> {
    ServerConfig config;

    public OAuth2BuilderRoute(ServerConfig config) {
        this.config = config;
    }

    @Override
    public void handle(RoutingContext event) {
        // 根据请求类型生成合适的 OAuth URL
        OAuth2Platform platform = this.config.getOAuth2Platforms().get(0);
        String url = platform.getAuthServer() +
                "?client_id=" +
                platform.getClientId() +
                "&scope=" +
                platform.getPermission() +
                "&state=" +
                UuidUtils.getUuid();
        String response = RepositionMessageService
                .buildMessage("200", Message.Status.SUCCESS, "url", url);
        log.info(response);
        event.response().putHeader("content-type", "application/json; charset=utf-8");
        event.response().end(response, "utf-8");
    }
}
