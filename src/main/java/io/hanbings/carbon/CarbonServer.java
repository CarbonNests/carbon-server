package io.hanbings.carbon;

import io.hanbings.carbon.common.content.Carbon;
import io.hanbings.carbon.common.config.ConfiguringStartup;
import io.hanbings.carbon.controller.ServerController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarbonServer {
    public static void main(String[] args) {
        log.info(Carbon.LOGO);
        // 初始化配置
        ConfiguringStartup.loadConfig();
        // 转交控制权
        new ServerController(ConfiguringStartup.getConfig()).serve();
    }
}
