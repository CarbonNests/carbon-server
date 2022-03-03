package io.hanbings.carbon.interfaces;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

public interface WebServerService extends Service {
    void router(HttpMethod method, String path, Handler<RoutingContext> handler);

    void router(HttpMethod method, String path, Handler<RoutingContext>... handler);
}
