package io.hanbings.carbon.web;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.web.interfaces.WebServerService;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServerService implements WebServerService {
    ServiceContainer container;
    Vertx vertx;
    Router router;
    HttpServer server;

    public HttpServerService(ServiceContainer container) {
        this.container = container;
    }

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
        container.getTaskService().thread(() -> {
            this.server.listen(container.getConfigService().getConfigContainer().getServerPort());
            log.info("web server running on port {}"
                    , container.getConfigService().getConfigContainer().getServerPort());
        });
        log.info("web server service started.");
    }

    @Override
    public void stop() {
        log.info("web server service stopped.");
    }
}
