package io.hanbings.carbon.config;

import io.hanbings.carbon.CarbonServer;
import io.hanbings.carbon.config.event.CarbonServerBootstrapListener;
import io.hanbings.carbon.config.event.ConfigServiceLoadedEvent;
import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.interfaces.ServiceBootloader;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class ConfigServiceBootloader implements ServiceBootloader {
    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void load(ServiceContainer container) {
        // 初始化配置文件 存在则读取 不存在则释放
        File file = new File("config.json");
        if (!file.exists()) {
            try {
                file.createNewFile();
                Objects.requireNonNull(CarbonServer.class.getResourceAsStream("/config.json")).
                        transferTo(new FileOutputStream(file));
                log.warn("config.json was create, you need check it and input value.");
                System.exit(0);
            } catch (IOException exception) {
                log.error("file io error. break your server.", exception);
                System.exit(1);
            }
        }
        // 注入配置文件服务
        container.setConfigService(new GsonService(container));
    }

    @Override
    public void events(ServiceContainer container) {
        container.getEventBus().registerEvent(new ConfigServiceLoadedEvent(container));
    }

    @Override
    public void listeners(ServiceContainer container) {
        container.getEventBus().registerListener(new CarbonServerBootstrapListener());
    }
}
