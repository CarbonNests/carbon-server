package io.hanbings.carbon.service;

import io.hanbings.carbon.container.ServerConfig;
import io.hanbings.carbon.service.interfaces.Service;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VertxService implements Service {
    Vertx vertx;
    HttpServer server;
    Router router;
    ServerConfig config;

    private VertxService(ServerConfig config) {
        this.config = config;
    }

    public static VertxService create(ServerConfig config) {
        return new VertxService(config);
    }

    @Override
    public void init() {
        // 初始化 VertX
        this.vertx = Vertx.vertx();
        // 创建 VertX 服务器
        this.server = vertx.createHttpServer();
        // 创建路由
        this.router = Router.router(vertx);
        // 设置处理器
        this.server.requestHandler(router);
        // 绑定端口
        server.listen(config.getServerPort());
        log.info("web server is running on port {}", config.getServerPort());

    }

    @Override
    public void close() {
        server.close();
    }
}
