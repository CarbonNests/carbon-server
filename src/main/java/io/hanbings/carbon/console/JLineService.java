package io.hanbings.carbon.console;

import io.hanbings.carbon.console.command.StopCommand;
import io.hanbings.carbon.console.interfaces.ConsoleService;
import io.hanbings.carbon.container.ServiceContainer;
import lombok.extern.slf4j.Slf4j;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class JLineService implements ConsoleService {
    ServiceContainer container;
    Terminal terminal;
    LineReader lineReader;

    public JLineService(ServiceContainer container) {
        this.container = container;
    }

    @Override
    public void console() {
        String prompt = "console> ";
        try {
            while (true) {
                String line = lineReader.readLine(prompt);
                if (Objects.equals(line, "stop")) {
                    new StopCommand().command(null, container);
                }
                log.info(line);
            }
        } catch (UserInterruptException | EndOfFileException exception) {
            log.info("stopping server. bye.");
        }
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
        log.info("entry console mode. type 'stop' to stop server.");
        this.console();
    }

    @Override
    public void stop() {
        log.info("console service stopped.");
    }
}
