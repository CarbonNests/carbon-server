package io.hanbings.carbon.config;

import com.google.gson.Gson;
import io.hanbings.carbon.common.util.GsonUtils;
import io.hanbings.carbon.config.interfaces.ConfigService;
import io.hanbings.carbon.container.ConfigContainer;
import io.hanbings.carbon.container.ServiceContainer;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;

@Slf4j
public class GsonService implements ConfigService {
    ServiceContainer container;
    Gson gson = GsonUtils.getGson();
    ConfigContainer config;

    public GsonService(ServiceContainer container) {
        this.container = container;
    }

    @Override
    public void loadConfig() {
        File file = new File("config.json");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            log.info("config read from disk success.");
            config = gson.fromJson(reader, ConfigContainer.class);
            // 反射检查服务器配置任意变量是否为空
            Field[] fields = config.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(config) == null) {
                    log.error("config error, field {} is null.", field.getName());
                    System.exit(1);
                }
            }
            log.info("config check success.");
        } catch (FileNotFoundException exception) {
            log.error("config.json file not found.", exception);
        } catch (IllegalAccessException exception) {
            log.error("config illegal access.", exception);
        }
    }

    @Override
    public void saveConfig() {
        File file = new File("config.json");
        String json = gson.toJson(config);
        try {
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(json.getBytes());
            log.info("config was saved.");
        } catch (IOException exception) {
            log.error("config save error.", exception);
        }
    }

    @Override
    public void reloadConfig() {
        this.loadConfig();
        log.info("config was reloaded.");
    }

    @Override
    public ConfigContainer getConfigContainer() {
        return config;
    }

    @Override
    public void start() {
        this.loadConfig();
        log.info("config service started.");
    }

    @Override
    public void stop() {
        this.saveConfig();
        log.info("config service stopped.");
    }
}
