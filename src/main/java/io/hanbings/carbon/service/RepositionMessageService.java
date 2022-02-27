package io.hanbings.carbon.service;


import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class RepositionMessageService {
    static Gson gson = new Gson();

    private RepositionMessageService() { }

    public static String buildMessage(String code, String message) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("message", message);
        return gson.toJson(map);
    }

    public static String buildMessage(String code, String message, Map<String, String> map) {
        map.put("code", code);
        map.put("message", message);
        return gson.toJson(map);
    }

    public static String buildMessage(String code, String message, String... more) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("message", message);
        for (int count = 0; count < more.length; count = count + 2) {
            map.put(more[count], more[count + 1]);
        }
        return gson.toJson(map);
    }
}
