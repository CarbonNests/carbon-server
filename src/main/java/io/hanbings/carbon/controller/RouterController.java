package io.hanbings.carbon.controller;

import io.hanbings.carbon.common.content.OAuth2PlatformType;
import io.hanbings.carbon.config.ConfiguringStartup;
import io.hanbings.carbon.data.OAuth2Platform;
import io.hanbings.carbon.interfaces.CacheService;
import io.hanbings.carbon.interfaces.ConsoleService;
import io.hanbings.carbon.interfaces.Controller;
import io.hanbings.carbon.interfaces.DatabaseService;
import io.hanbings.carbon.interfaces.TaskService;
import io.hanbings.carbon.interfaces.WebServerService;
import io.hanbings.carbon.router.OAuth2PlatformCallbackHandler;
import io.hanbings.carbon.router.OAuth2PlatformHandler;
import io.hanbings.carbon.router.OAuth2ServiceCallbackHandler;
import io.hanbings.carbon.router.OAuth2ServiceHandler;
import io.vertx.core.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public record RouterController(TaskService taskService,
                               ConsoleService consoleService,
                               DatabaseService databaseService,
                               WebServerService webServerService,
                               CacheService cacheService) implements Controller {
    @Override
    public void serve() {
        // 初始化
        webServerService.start();

        // 更新 OAuth2 平台信息
        Map<OAuth2PlatformType, OAuth2Platform> auths = new ConcurrentHashMap<>();
        ConfiguringStartup.getConfig().getOAuth2Platforms().forEach(platform -> {
            auths.put(platform.getPlatform(), platform);
        });
        // 路由
        // 用于登录平台的 OAuth2 认证
        webServerService.router(HttpMethod.GET,
                "/v0/login/oauth2/:platform/authorize", new OAuth2PlatformHandler(auths, cacheService));
        webServerService.router(HttpMethod.GET,
                "/v0/login/oauth2/:platform/token", new OAuth2PlatformCallbackHandler(cacheService));
        // 平台提供的 OAuth2 服务
        webServerService.router(HttpMethod.GET,
                "/v0/oauth2/authorize", new OAuth2ServiceHandler());
        webServerService.router(HttpMethod.GET,
                "/v0/oauth2/token", new OAuth2ServiceCallbackHandler());
    }
}
