package io.hanbings.carbon.database;

import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.database.interfaces.DatabaseService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("SpellCheckingInspection")
public class MongoDBService implements DatabaseService {
    ServiceContainer container;

    public MongoDBService(ServiceContainer container) {
        this.container = container;
    }

    @Override
    public void create(Object object) {

    }

    @Override
    public void update(Object object) {

    }

    @Override
    public <T> T read(Class<T> clazz, Object id) {
        return null;
    }

    @Override
    public void delete(Object object) {

    }

    @Override
    public void start() {
        log.info("database service started.");
    }

    @Override
    public void stop() {
        log.info("database service stopped.");
    }
}
