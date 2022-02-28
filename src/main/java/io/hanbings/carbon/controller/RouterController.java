package io.hanbings.carbon.controller;

import io.hanbings.carbon.container.ServerConfig;
import io.hanbings.carbon.controller.interfaces.Controller;
import io.hanbings.carbon.router.OAuth2BuilderRoute;
import io.hanbings.carbon.router.OAuth2CallbackRoute;
import io.hanbings.carbon.router.ProtectRoute;
import io.hanbings.carbon.service.VertxService;
import io.vertx.core.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RouterController implements Controller {
    ServerConfig config;
    TaskController taskController;
    VertxService server;

    public RouterController(ServerConfig config, TaskController taskController) {
        this.config = config;
        this.taskController = taskController;
    }

    @Override
    public void run() {
        log.info("RouterController run.");
        this.server = new VertxService(config.getServerPort());
        this.taskController.task(new Thread(() -> { server.run(); }));
        // 注册路由
        // 第三方 OAuth 登录相关
        // 生成第三方 OAuth 发送至前端
        this.server.addSimpleRoute(HttpMethod.GET, "/v0/login/oauth2", new OAuth2BuilderRoute(this.config));
        // 第三方 OAuth 鉴权完成后的回调地址
        this.server.addSimpleRoute(HttpMethod.GET, "/v0/login/oauth2/callback", new OAuth2CallbackRoute(this.config));
        // 在平台注销当前登录的 token
        this.server.addSimpleRoute(HttpMethod.POST, "/v0/logout", new OAuth2BuilderRoute(this.config));
        // 平台 OAuth 鉴权相关
        // 平台 OAuth 入口
        this.server.addSimpleRoute(HttpMethod.POST, "/v0/oauth2/authorize", new OAuth2BuilderRoute(this.config));
        // 帐号信息
        this.server.addSimpleRoute(HttpMethod.POST, "/v0/account/change", new OAuth2BuilderRoute(this.config));
        this.server.addSimpleRoute(HttpMethod.POST, "/v0/account/get", new OAuth2BuilderRoute(this.config));
        // 屏蔽无效请求
        this.server.addSimpleRoute(HttpMethod.GET, "/*", new ProtectRoute());
        this.server.addSimpleRoute(HttpMethod.POST, "/*", new ProtectRoute());
    }

    @Override
    public void stop() {
        log.info("RouterController stop.");
        server.stop();
    }
}
