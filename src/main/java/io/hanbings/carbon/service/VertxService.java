package io.hanbings.carbon.service;

import io.hanbings.carbon.service.interfaces.RunnableService;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class VertxService implements RunnableService {
    Vertx vertx;
    HttpServer server;
    Router router;
    int port;
    Set<String> allowedHeaders;
    Set<HttpMethod> allowedMethods;

    private VertxService() {}

    @SuppressWarnings("SpellCheckingInspection")
    public VertxService(int port) {
        // 初始化 VertX
        this.vertx = Vertx.vertx();
        // 创建 VertX 服务器
        this.server = vertx.createHttpServer();
        // 创建路由
        this.router = Router.router(vertx);
        // 设置处理器
        this.server.requestHandler(router);
        // 设置端口
        this.port = port;
        // 初始化跨域配置
        allowedHeaders = new HashSet<>();
        allowedMethods = new HashSet<>();
        allowedHeaders.add("x-requested-with");
        allowedHeaders.add("Access-Control-Allow-Origin");
        allowedHeaders.add("origin");
        allowedHeaders.add("Content-Type");
        allowedHeaders.add("accept");
        allowedHeaders.add("X-PINGARUNER");
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.POST);
        allowedMethods.add(HttpMethod.OPTIONS);
        // 设置默认为允许所有域
        router.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods));
    }

    @Override
    public void run() {
        log.info("Web server is running on port {}", port);
        // 绑定端口
        server.listen(this.port);
    }

    @Override
    public void stop() {
        server.close();
    }

    public void addSimpleRoute(HttpMethod method, String path, Handler<RoutingContext> handler) {
        this.router.route(method, path).handler(handler);
    }

    public void addFileUploadRoute(HttpMethod method, String path, Handler<RoutingContext> handler) {
        this.router.route(method, path).handler(BodyHandler.create().setUploadsDirectory("temp")).handler(handler);
    }

    public void delRoute(String path) {
        this.router.delete(path);
    }
}
