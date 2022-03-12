package io.hanbings.carbon.common.config;

import com.google.gson.Gson;
import io.hanbings.carbon.CarbonServer;
import io.hanbings.carbon.common.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Objects;

@Slf4j
public class ConfiguringStartup {
    static Gson gson = GsonUtils.getGson();
    static Config config = new Config();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void loadConfig() {
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


        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            log.info("config read from disk success.");
            config = gson.fromJson(reader, Config.class);
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

    public static void saveConfig() {
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

    public static void reloadConfig() {
        loadConfig();
        log.info("config was reloaded.");
    }

    public static Config getConfig() {
        return config;
    }
}
