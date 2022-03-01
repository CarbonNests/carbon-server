package io.hanbings.carbon.event;

import io.hanbings.carbon.event.interfaces.Blockable;
import io.hanbings.carbon.event.interfaces.Cancellable;
import io.hanbings.carbon.event.interfaces.EventHandler;
import io.hanbings.carbon.event.interfaces.Listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unused")
public class EventBus {
    private final Map<Class<? extends Event>, RegisteredListener> handlers = new ConcurrentHashMap<>();

    public void callEvent(Event event) {
        if (handlers.containsKey(event.getClass())) {
            for (RegisteredHandler handler : handlers.get(event.getClass()).getHandlerList()) {
                if (event instanceof Blockable && ((Blockable) event).isBlocked()) {
                    return;
                }
                // isIgnoreCancelled 为 true 继续执行
                if (event instanceof Cancellable
                        && ((Cancellable) event).isCancelled()
                        && !handler.isIgnoreCancelled()) {
                    continue;
                }
                try {
                    handler.getMethod().invoke(handler.getListener(), event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public RegisteredListener getEventHandler(Event event) {
        return handlers.get(event.getClass());
    }

    public void registerEvent(Event event) {
        if (!handlers.containsKey(event.getClass())) {
            handlers.put(event.getClass(), new RegisteredListener());
        }
    }

    public void unregisterEvent(Event event) {
        handlers.remove(event.getClass());
    }

    public void registerListener(Listener listener) {
        Class<?> clazz = listener.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                final Class<?> event;
                method.setAccessible(true);
                event = method.getParameterTypes()[0];
                if (handlers.containsKey(event)) {
                    EventHandler annotation = method.getAnnotation(EventHandler.class);
                    handlers.get(event).addHandler(
                            new RegisteredHandler(annotation.priority()
                                    , annotation.ignoreCancelled(), listener, method));
                }
            }
        }
    }

    public void unregisterListener(Listener listener) {
        Class<?> clazz = listener.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                final Class<?> event;
                method.setAccessible(true);
                event = method.getParameterTypes()[0];
                if (handlers.containsKey(event)) {
                    EventHandler annotation = method.getAnnotation(EventHandler.class);
                    handlers.get(event).removeHandler(
                            new RegisteredHandler(annotation.priority()
                                    , annotation.ignoreCancelled(), listener, method));
                }
            }
        }
    }
}