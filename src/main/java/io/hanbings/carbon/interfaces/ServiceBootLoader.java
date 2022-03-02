package io.hanbings.carbon.interfaces;

import io.hanbings.carbon.container.ServiceContainer;

public interface ServiceBootLoader {
    void load(ServiceContainer container);

    void events(ServiceContainer container);

    void listeners(ServiceContainer container);
}
