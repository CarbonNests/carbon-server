package io.hanbings.carbon;

import io.hanbings.carbon.common.content.Carbon;
import io.hanbings.carbon.common.util.FileUtils;
import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.event.EventBus;
import io.hanbings.carbon.interfaces.ServiceBootloader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 世界名画级扫包现场
 */
@Slf4j
public class CarbonServer {
    public static void main(String[] args) {
        log.info("waiting container...");
        ServiceContainer container = ServiceContainer.registry(new EventBus());
        // 将事件创建
        // CarbonServerBootstrapEvent 内不能处理逻辑 仅仅是让事件被消费
        // 因为此时容器中仅注入了 EventBus
        CarbonServerBootstrapEvent event = new CarbonServerBootstrapEvent(container);
        // 根据 EventBus 的特性 在注册事件前注册该事件的监听器将会被忽略
        container.getEventBus().registerEvent(event);

        /*
         * 往容器注入 EventBus <br>
         * 通过注解扫描整个包下的类 <br>
         * 反射实现 ServiceBootLoader 的类进行事件注册 <br>
         * 触发 CarbonServerBootstrapEvent 后 沿着启动链初始化各个服务 <br>
         * config service -> database service -> task service
         * -> webserver service -> console service <br>
         * 该顺序需要人为规定 即下一个 Service 监听上一个 Service 的 Loaded 事件 <br>
         * 这个过程将会注入依赖  最后将控制权交还 bootstrap event <br>
         * 当然 大多数事件都应该在逻辑代码执行前触发而不是完成后 <br>
         * 这里的情况是事件既不能阻断也不能取消 同时不允许其他 Service 干扰加载过程
         */

        // 收集类路径下所有的类
        List<String> collect = CarbonServer.paths();
        Map<Object, Method> events = new HashMap<>();
        Map<Object, Method> listeners = new HashMap<>();
        Map<Object, Method> loads = new HashMap<>();


        // 加载类
        @SuppressWarnings("SpellCheckingInspection")
        List<Class<?>> clazzes = new ArrayList<>();
        try {
            for (String name : collect) {
                clazzes.add(Thread.currentThread().getContextClassLoader().loadClass(name));
            }
        } catch (ClassNotFoundException exception) {
            log.error("unknown class not found error.", exception);
        }

        // 引导注册服务事件与服务监听器
        try {
            for (Class<?> clazz : clazzes) {
                if (!clazz.isInterface() && ServiceBootloader.class.isAssignableFrom(clazz)) {
                    ServiceBootloader bootLoader = (ServiceBootloader) clazz.getDeclaredConstructor().newInstance();
                    events.put(bootLoader, clazz.getDeclaredMethod("events", ServiceContainer.class));
                    listeners.put(bootLoader, clazz.getDeclaredMethod("listeners", ServiceContainer.class));
                    loads.put(bootLoader, clazz.getDeclaredMethod("load", ServiceContainer.class));
                    log.info("call service boot loader: {} ", clazz.getName());
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | InstantiationException exception) {
            log.error("service bootloader load() method error.", exception);
        }

        // events() -> listeners() -> load()
        log.info("waiting total {} service boot loader", loads.size());
        CarbonServer.invokes(events, container);
        CarbonServer.invokes(listeners, container);
        CarbonServer.invokes(loads, container);

        // 显示 LOGO
        log.info(Carbon.LOGO);

        // 加载完成后触发事件
        log.info("toward the open sea.");
        container.getEventBus().callEvent(event);
    }

    static List<String> paths() {
        @SuppressWarnings("SpellCheckingInspection")
        String artifact = "io.hanbings.carbon";
        String path = CarbonServer.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        List<String> collect = new ArrayList<>();

        // 遍历 String 带路径类名
        if (path.endsWith(".jar")) {
            try {
                Enumeration<URL> urlEnumeration = Thread.currentThread()
                        .getContextClassLoader().getResources(artifact.replace(".", "/"));
                while (urlEnumeration.hasMoreElements()) {
                    URL url = urlEnumeration.nextElement();
                    JarURLConnection connection = (JarURLConnection) url.openConnection();
                    JarFile jarFile = connection.getJarFile();
                    Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                    while (jarEntryEnumeration.hasMoreElements()) {
                        JarEntry entry = jarEntryEnumeration.nextElement();
                        String jarEntryName = entry.getName();
                        if (jarEntryName.contains(".class")
                                && jarEntryName.replaceAll("/", ".").startsWith(artifact)) {
                            collect.add(jarEntryName.substring(0,
                                    jarEntryName.lastIndexOf(".")).replace("/", "."));
                        }
                    }
                }
            } catch (IOException exception) {
                log.error("unknown io error.", exception);
            }
        } else {
            String file = Objects.requireNonNull(CarbonServer.class.getResource("/")).getPath()
                    + artifact.replace(".", "/");
            FileUtils.getFiles(file).stream().filter(f -> f.getName().endsWith(".class")).forEach(f -> {
                collect.add(f.getPath()
                        .replace(Objects.requireNonNull(CarbonServer.class.getResource("/")).getPath()
                                .replace("/", "\\")
                                .replaceFirst("\\\\", ""), "")
                        .replace("\\", ".")
                        .replace(".class", ""));
            });
        }
        return collect;
    }

    static void invokes(Map<Object, Method> map, ServiceContainer container) {
        for (Map.Entry<Object, Method> entry : map.entrySet()) {
            try {
                entry.getValue().invoke(entry.getKey(), container);
            } catch (IllegalAccessException | InvocationTargetException exception) {
                log.error("unknown error. fail load service {} ", entry.getKey().getClass().getName(), exception);
            }
        }
    }
}
