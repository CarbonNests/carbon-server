package io.hanbings.carbon.interfaces;

public interface DatabaseService extends Service {
    void create(String database, Object data);

    void read(String database, Object data);

    void update(String database, Object data);

    void delete(String database, Object data);
}
