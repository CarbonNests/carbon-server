package io.hanbings.carbon.console;

import io.hanbings.carbon.console.event.ConsoleServiceLoadedEvent;
import io.hanbings.carbon.console.event.StopCommandEvent;
import io.hanbings.carbon.console.event.WebServerServiceLoadedListener;
import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.interfaces.ServiceBootloader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleServiceBootloader implements ServiceBootloader {
    @Override
    public void load(ServiceContainer container) {

    }

    @Override
    public void events(ServiceContainer container) {
        // 注册事件
        container.getEventBus().registerEvent(new ConsoleServiceLoadedEvent(container));
        container.getEventBus().registerEvent(new StopCommandEvent(container));
    }

    @Override
    public void listeners(ServiceContainer container) {
        container.getEventBus().registerListener(new WebServerServiceLoadedListener());
    }
}
