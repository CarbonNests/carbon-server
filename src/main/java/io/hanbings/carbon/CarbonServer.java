package io.hanbings.carbon;

import io.hanbings.carbon.common.content.Carbon;
import io.hanbings.carbon.common.util.FileUtils;
import io.hanbings.carbon.event.Event;
import io.hanbings.carbon.event.EventBus;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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

        // 收集类路径下所有的类
        @SuppressWarnings("SpellCheckingInspection")
        String artifact = "io.hanbings.carbon";
        String path = CarbonServer.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        List<String> classes = new ArrayList<>();

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
                            classes.add(jarEntryName.substring(0,
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
                classes.add(f.getPath()
                        .replace(Objects.requireNonNull(CarbonServer.class.getResource("/")).getPath()
                                .replace("/", "\\")
                                .replaceFirst("\\\\", ""), "")
                        .replace("\\", ".")
                        .replace(".class", ""));
            });
        }

        // 反射整理类
        // 过滤 common包 和 event 包
        @SuppressWarnings("SpellCheckingInspection")
        List<String> collect = classes.stream().filter(c -> !c.contains("io.hanbings.carbon.event")
                && !c.contains("io.hanbings.carbon.common")).toList();

        // 加载剩余的类
        @SuppressWarnings("SpellCheckingInspection")
        List<Class<?>> clazzes = new ArrayList<>();
        try {
            for (String className : collect) {
                clazzes.add(Thread.currentThread().getContextClassLoader().loadClass(className));
            }
        } catch (ClassNotFoundException exception) {
            log.error("unknown class not found error.", exception);
        }

        // 扫描加载的类是否包含继承 Event 的类
        clazzes.stream().filter(c -> c.getSuperclass().equals(Event.class)).forEach(c -> {
            // 强转 Event 注册到事件总线中
            log.info("register event {}", c.getName());
        });

        //bus.callEvent(new CarbonServerBootstrapEvent(new ServiceContainer()));

    }
}
