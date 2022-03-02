package io.hanbings.carbon.console;

import io.hanbings.carbon.console.event.ConsoleLoadingEvent;
import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.interfaces.ServiceBootLoader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JLineServiceBootLoader implements ServiceBootLoader {
    @Override
    public void load(ServiceContainer container) {

    }

    @Override
    public void events(ServiceContainer container) {
        // 注册事件
        ConsoleLoadingEvent event = new ConsoleLoadingEvent();
        container.getEventBus().registerEvent(event);
    }

    @Override
    public void listeners(ServiceContainer container) {

    }
}
