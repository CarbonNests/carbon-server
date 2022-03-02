package io.hanbings.carbon.console.interfaces;

import io.hanbings.carbon.container.ServiceContainer;

public interface Command {
    void command(String[] args, ServiceContainer container);
}
