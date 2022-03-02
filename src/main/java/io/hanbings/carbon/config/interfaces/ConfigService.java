package io.hanbings.carbon.config.interfaces;

import io.hanbings.carbon.container.ConfigContainer;
import io.hanbings.carbon.interfaces.Service;

public interface ConfigService extends Service {
    void loadConfig();

    void saveConfig();

    void reloadConfig();

    ConfigContainer getConfigContainer();
}
