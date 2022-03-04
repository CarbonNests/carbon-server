package io.hanbings.carbon.service;

import io.hanbings.carbon.data.Cache;
import io.hanbings.carbon.interfaces.CacheService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MapCacheService implements CacheService {
    @Getter
    Map<String, Cache> caches;

    @Override
    public void put(String key, Cache cache) {
        caches.put(key, cache);
    }

    @Override
    public Cache get(String key) {
        return caches.getOrDefault(key, null);
    }

    @Override
    public void remove(String key) {
        caches.remove(key);
    }

    @Override
    public void clear() {
        caches.clear();
    }

    @Override
    public void start() {
        caches = new ConcurrentHashMap<>();
        log.info("cache service started.");
    }

    @Override
    public void stop() {
        this.clear();
        log.info("cache service stopped.");
    }
}
