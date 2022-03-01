package io.hanbings.carbon.router;

import io.hanbings.carbon.common.util.UuidUtils;
import io.hanbings.carbon.container.ServerConfig;
import io.hanbings.carbon.container.ServerDependencyContainer;
import io.hanbings.carbon.data.OAuth2Platform;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.jmx.Server;

@Slf4j
public class OAuth2BuilderRoute implements Handler<RoutingContext> {
    ServerDependencyContainer container;

    public OAuth2BuilderRoute(ServerDependencyContainer container) {
        this.container = container;
    }

    @Override
    public void handle(RoutingContext event) {
        // 根据请求类型生成合适的 OAuth URL
        OAuth2Platform platform = this.container.getConfig().getOAuth2Platforms().get(0);
        String state = UuidUtils.getUuid();
        String url = platform.getAuthServer() +
                "?client_id=" +
                platform.getClientId() +
                "&scope=" +
                platform.getPermission() +
                "&state=" +
                state;
        String response = url;
        event.response().putHeader("content-type", "application/json; charset=utf-8");
        event.response().end(response, "utf-8");
    }
}
