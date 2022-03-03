package io.hanbings.carbon.service;

import io.hanbings.carbon.interfaces.ConsoleService;
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
    Terminal terminal;
    LineReader lineReader;

    @Override
    public void console() {
        String prompt = "console> ";
        try {
            while (true) {
                String line = lineReader.readLine(prompt);
                if (Objects.equals(line, "stop")) {
                    break;
                }
                log.info(line);
            }
        } catch (UserInterruptException | EndOfFileException exception) {
            log.info("stopping server. bye.");
        }
        this.stop();
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
        System.exit(0);
    }
}
