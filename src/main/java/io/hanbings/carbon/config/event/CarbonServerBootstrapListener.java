package io.hanbings.carbon.config.event;

import io.hanbings.carbon.CarbonServerBootstrapEvent;
import io.hanbings.carbon.config.GsonService;
import io.hanbings.carbon.console.event.StopCommandEvent;
import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.event.interfaces.EventHandler;
import io.hanbings.carbon.event.interfaces.Listener;

public class CarbonServerBootstrapListener implements Listener {
    @EventHandler
    public void onCarbonServerBootstrap(CarbonServerBootstrapEvent event) {
        // 注入配置服务
        ServiceContainer container = event.getServiceContainer();
        container.setConfigService(new GsonService(container));
        // 家加载配置文件
        container.getConfigService().start();
        // 触发完成配置加载事件
        container.getEventBus().callEvent(new ConfigServiceLoadedEvent(event.getServiceContainer()));
    }

    @EventHandler
    public void onStopCommand(StopCommandEvent event) {
        ServiceContainer container = event.getServiceContainer();
        container.getConfigService().stop();
    }

}
