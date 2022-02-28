package io.hanbings.carbon.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class ResponseMessageService {
    static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    private ResponseMessageService() {
    }

    public static ResponseMessageService create() {
        return new ResponseMessageService();
    }

    public String buildMessage(String code, String message) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("message", message);
        return gson.toJson(map);
    }

    public String buildMessage(String code, String message, Map<String, String> map) {
        map.put("code", code);
        map.put("message", message);
        return gson.toJson(map);
    }

    public String buildMessage(String code, String message, String... more) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("message", message);
        for (int count = 0; count < more.length; count = count + 2) {
            map.put(more[count], more[count + 1]);
        }
        return gson.toJson(map);
    }
}
