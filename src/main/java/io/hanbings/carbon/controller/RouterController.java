package io.hanbings.carbon.controller;

import io.hanbings.carbon.container.ServerDependencyContainer;
import io.hanbings.carbon.container.ServerService;
import io.hanbings.carbon.controller.interfaces.Controller;
import io.hanbings.carbon.router.OAuth2BuilderRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import org.apache.logging.log4j.core.jmx.Server;

public class RouterController implements Controller {
    ServerDependencyContainer container;

    private RouterController(ServerDependencyContainer container) {
        this.container = container;
    }

    public static RouterController create(ServerDependencyContainer container) {
        return new RouterController(container);
    }

    @Override
    public void start() {
        // 使用线程启动 vertx 避免卡死主线程
        container.getService().getTaskService().getThreadPool().execute(() -> {
            container.getService().getVertxService().init();
        });

        container.getService().getTaskService().getThreadPool().execute(() -> {
            Router router = container.getService().getVertxService().getRouter();
            // 生成第三方 OAuth 发送至前端
            router.route(HttpMethod.GET, "/v0/login/oauth2").handler(new OAuth2BuilderRoute(container));
        });

        // 异步添加路由

    }

    @Override
    public void stop() {
        container.getService().getVertxService().close();
    }
}
