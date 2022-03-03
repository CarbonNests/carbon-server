package io.hanbings.carbon.controller;

import io.hanbings.carbon.common.content.OAuth2PlatformType;
import io.hanbings.carbon.config.ConfiguringStartup;
import io.hanbings.carbon.data.OAuth2Platform;
import io.hanbings.carbon.interfaces.ConsoleService;
import io.hanbings.carbon.interfaces.Controller;
import io.hanbings.carbon.interfaces.DatabaseService;
import io.hanbings.carbon.interfaces.TaskService;
import io.hanbings.carbon.interfaces.WebServerService;
import io.hanbings.carbon.router.OAuth2CallbackHandler;
import io.hanbings.carbon.router.OAuth2Handler;
import io.vertx.core.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public record RouterController(TaskService taskService,
                               ConsoleService consoleService,
                               DatabaseService databaseService,
                               WebServerService webServerService) implements Controller {
    @Override
    public void serve() {
        // 初始化
        webServerService.start();

        // 更新 OAuth2 平台信息
        Map<OAuth2PlatformType, OAuth2Platform> auths = new HashMap<>();
        ConfiguringStartup.getConfig().getOAuth2Platforms().forEach(platform -> {
            auths.put(platform.getPlatform(), platform);
        });
        // 路由
        webServerService.router(HttpMethod.GET, "/v0/login/oauth2", new OAuth2Handler(auths));
        webServerService.router(HttpMethod.GET, "/v0/login/oauth2/callback", new OAuth2CallbackHandler());
    }
}
