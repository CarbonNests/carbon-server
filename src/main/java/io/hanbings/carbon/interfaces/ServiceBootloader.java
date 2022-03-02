package io.hanbings.carbon.interfaces;

import io.hanbings.carbon.container.ServiceContainer;

public interface ServiceBootloader {
    /**
     * 仅用于初始化需要的资源 因为 EventBus 并不能保证各个 Service 加载顺序是按照依赖规则进行<br>
     * 可作为对资源的检查 如检查配置文件是否存在 端口是否被占用等 <br>
     * <strong>禁止</strong>用于直接初始化 Service 对象 <br>
     * 初始化 Service 应创建一个监听器 监听本 Service 需要依赖的 Service 对应初始化完成事件 等待调用 <br>
     * 然后在 listeners() 中注册它
     *
     * @param container 容器
     */
    void load(ServiceContainer container);

    /**
     * 用于加载事件
     *
     * @param container 容器
     */
    void events(ServiceContainer container);

    /**
     * 用于加载监听器
     *
     * @param container 容器
     */
    void listeners(ServiceContainer container);
}
