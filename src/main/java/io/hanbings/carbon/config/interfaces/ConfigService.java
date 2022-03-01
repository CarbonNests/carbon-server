package io.hanbings.carbon.config.interfaces;

import io.hanbings.carbon.container.ConfigContainer;

public interface ConfigService {
    void loadConfig();
    void saveConfig();
    void reloadConfig();

    <V> V get(V key);
    ConfigContainer getConfigContainer();
}
