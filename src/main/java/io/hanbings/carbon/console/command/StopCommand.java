package io.hanbings.carbon.console.command;

import io.hanbings.carbon.console.event.StopCommandEvent;
import io.hanbings.carbon.console.interfaces.Command;
import io.hanbings.carbon.container.ServiceContainer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StopCommand implements Command {
    @Override
    public void command(String[] args, ServiceContainer container) {
        container.getEventBus().callEvent(new StopCommandEvent(container));
        System.exit(0);
    }
}
