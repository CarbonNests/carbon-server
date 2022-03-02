package io.hanbings.carbon.database.interfaces;

import io.hanbings.carbon.interfaces.Service;

public interface DatabaseService extends Service {
    void create(Object object);

    void update(Object object);

    <T> T read(Class<T> clazz, Object id);

    void delete(Object object);
}
