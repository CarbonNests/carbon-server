package io.hanbings.carbon;

import io.hanbings.carbon.common.content.Carbon;
import io.hanbings.carbon.event.EventBus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarbonServer {
    public static void main(String[] args) {
        log.info(Carbon.LOGO);

        EventBus bus = new EventBus();

        /*
         * 通过注解扫描整个包下的类 <br>
         * 继承 Event 的类注册到事件总线中 <br>
         * 实现 Listener 的类且存在 @EventHandler 一并注册到事件总线中 <br>
         * 触发 CarbonServerBootstrapEvent 后 沿着启动链初始化各个服务 <br>
         * console service -> webserver service -> task service
         * -> database service -> config service <br>
         * 这个过程将会倒序注入依赖 即先执行  config service 的逻辑再执行 database service 的逻辑 <br>
         * 以此类推 最后将控制权交还 bootstrap event <br>
         * 因为 Event 是先于处理逻辑触发的
         */

        //收集类路径


        //bus.callEvent(new CarbonServerBootstrapEvent(new ServiceContainer()));

    }
}
