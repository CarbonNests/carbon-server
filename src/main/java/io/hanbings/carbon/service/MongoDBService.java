package io.hanbings.carbon.service;

import io.hanbings.carbon.container.ServerConfig;
import io.hanbings.carbon.service.interfaces.Service;

@SuppressWarnings("SpellCheckingInspection")
public class MongoDBService implements Service {
    ServerConfig config;

    private MongoDBService(ServerConfig config) {
        this.config = config;
    }

    public static MongoDBService create(ServerConfig config) {
        return new MongoDBService(config);
    }


    @Override
    public void init() {

    }

    @Override
    public void close() {

    }
}
