package io.hanbings.carbon.controller;

import io.hanbings.carbon.controller.interfaces.Controller;
import lombok.extern.slf4j.Slf4j;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class CommandController implements Controller {
    List<Controller> controllers = new ArrayList<>();

    public CommandController(Controller... controllers) {
        this.controllers.addAll(List.of(controllers));
    }

    @Override
    public void run() {
        log.info("CommandController run.");
        log.info("Loading console...");
        // 阻塞主线程
        Terminal terminal = null;
        try {
            terminal = TerminalBuilder.builder()
                    .system(true)
                    .jna(true)
                    .color(true)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();

        String prompt = "console> ";
        try {
            while (true) {
                String line = lineReader.readLine(prompt);
                if (Objects.equals(line, "stop")) {
                    break;
                }
                log.info(line);
            }
        } catch (UserInterruptException ignored) {
            log.warn("User interrupt.");
        } catch (EndOfFileException exception) {
            log.info("Bye.");
        }
        this.stop();
    }

    @Override
    public void stop() {
        log.info("Stopping all controller.");
        this.controllers.forEach(Controller::stop);
        log.info("CommandController stop.");
    }
}
