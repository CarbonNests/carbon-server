package io.hanbings.carbon.interfaces;

public interface ConsoleService extends Service {
    void console();

    void command(String node, Command command);
}
