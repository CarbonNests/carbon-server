package io.hanbings.carbon.interfaces;

import io.hanbings.carbon.data.Cache;

public interface CacheService extends Service {
    public void put(String key, Cache cache);

    public Cache get(String key);

    public void remove(String key);

    public void clear();
}
