package io.hanbings.carbon.service;

import io.hanbings.carbon.interfaces.Command;
import io.hanbings.carbon.interfaces.ConsoleService;
import lombok.extern.slf4j.Slf4j;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JLineService implements ConsoleService {
    Terminal terminal;
    LineReader lineReader;
    Map<String, Command> commands = new HashMap<>();

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void console() {

        log.info("entry console mode. type 'stop' to stop server.");
        String prompt = "console> ";
        try {
            while (true) {
                String line = lineReader.readLine(prompt);
                // 解析异常输入
                String[] command = line.split(" ").length == 0
                        ? new String[]{line} : line.split(" ");
                if (commands.containsKey(command[0])) {
                    commands.get(line).command(command);
                } else {
                    log.info("command '{}' not found.", line);
                }
            }
        } catch (UserInterruptException | EndOfFileException exception) {
            log.info("stopping server. bye.");
        }
        this.stop();
    }

    @Override
    public void command(String node, Command command) {
        commands.put(node, command);
    }

    @Override
    public void start() {
        try {
            terminal = TerminalBuilder.builder()
                    .system(true)
                    .jna(true)
                    .color(true)
                    .build();
        } catch (IOException exception) {
            log.error("terminal build error.", exception);
        }

        lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
        log.info("console service started.");
    }

    @Override
    public void stop() {
        log.info("console service stopped.");
        System.exit(0);
    }
}
