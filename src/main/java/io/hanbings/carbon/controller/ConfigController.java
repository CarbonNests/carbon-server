package io.hanbings.carbon.controller;

import com.google.gson.Gson;
import io.hanbings.carbon.CarbonServer;
import io.hanbings.carbon.controller.interfaces.Controller;
import io.hanbings.carbon.data.ServerConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Objects;

@Slf4j
public class ConfigController implements Controller {
    ServerConfig config = new ServerConfig();
    Gson gson = new Gson();

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void run() {
        log.info("ConfigController run.");
        // 初始化配置文件 存在则读取 不存在则释放
        File file = new File("config.json");
        if (!file.exists()) {
            try {
                file.createNewFile();
                Objects.requireNonNull(CarbonServer.class.getResourceAsStream("/config.json")).
                        transferTo(new FileOutputStream(file));
            } catch (IOException exception) {
                log.error("file IO error.", exception);
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            config = gson.fromJson(reader, ServerConfig.class);
        } catch (FileNotFoundException exception) {
            log.error("file not found.", exception);
        }
        log.info("Config load success.");
    }

    @Override
    public void stop() {
        log.info("ConfigController stop.");
    }

    public ServerConfig getServerConfig() {
        return this.config;
    }
}
