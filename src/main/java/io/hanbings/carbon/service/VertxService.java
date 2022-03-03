package io.hanbings.carbon.service;

import io.hanbings.carbon.interfaces.TaskService;
import io.hanbings.carbon.interfaces.WebServerService;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class VertxService implements WebServerService {
    final int port;
    final TaskService task;
    Vertx vertx;
    Router router;
    HttpServer server;

    @Override
    public void start() {
        // 初始化 VertX
        this.vertx = Vertx.vertx();
        // 创建 VertX 服务器
        this.server = vertx.createHttpServer();
        // 创建路由
        this.router = Router.router(vertx);
        // 设置处理器
        this.server.requestHandler(router);
        // 线程启动 vertx
        task.thread(() -> {
            this.server.listen(port);
            log.info("web server running on port {}"
                    , port);
        });
        log.info("web server service started.");
    }

    @Override
    public void stop() {
        server.close();
        log.info("web server service stopped.");
    }


    @Override
    public void router(HttpMethod method, String path, Handler<RoutingContext> handler) {
        router.route(method, path).handler(handler);
    }

    @SafeVarargs
    @Override
    public final void router(HttpMethod method, String path, Handler<RoutingContext>... handler) {
        Arrays.stream(handler).forEach(route -> router.route(method, path).handler(route));
    }
}
