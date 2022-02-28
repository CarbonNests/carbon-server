package io.hanbings.carbon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.hanbings.carbon.common.content.AuthPlatformType;
import io.hanbings.carbon.common.content.AuthPlatformTypeAdapter;
import io.hanbings.carbon.container.ServerConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;

@Slf4j
public class ConfigReader {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static ServerConfig loadConfig() {
        // 初始化 Gson 以及枚举解析器
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(AuthPlatformType.class, new AuthPlatformTypeAdapter()).disableHtmlEscaping();
        Gson gson = builder.create();

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
            log.info("config read from disk success.");
            ServerConfig config = gson.fromJson(reader, ServerConfig.class);
            // 反射检查服务器配置任意变量是否为空
            Field[] fields = config.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(config) == null) {
                    log.error("config error, field {} is null.", field.getName());
                    System.exit(1);
                }
            }
            // 配置选项解析成功后返回
            return config;
        } catch (FileNotFoundException exception) {
            log.error("file not found.", exception);
        } catch (IllegalAccessException exception) {
            log.error("illegal access.", exception);
        }
        return null;
    }
}
