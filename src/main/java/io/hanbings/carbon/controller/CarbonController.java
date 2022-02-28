package io.hanbings.carbon.controller;

import io.hanbings.carbon.container.ServerDependencyContainer;
import io.hanbings.carbon.controller.interfaces.Controller;
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
public class CarbonController implements Controller {
    ServerDependencyContainer container;

    private CarbonController(ServerDependencyContainer container) {
        this.container = container;
    }

    public static CarbonController create(ServerDependencyContainer container) {
        return new CarbonController(container);
    }

    @Override
    public void start() {
        log.info("starting controller");
        // 移交权限后逐个启动控制器
        container.getController().getTaskController().start();
        container.getController().getDatabaseController().start();
        container.getController().getRouterController().start();

        // 启动完成后检查是否有错误
        // 启动命令行
        Terminal terminal = null;
        try {
            terminal = TerminalBuilder.builder()
                    .system(true)
                    .jna(true)
                    .color(true)
                    .build();
        } catch (IOException exception) {
            log.error("failed to create terminal.", exception);
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
        log.info("stopping controller");
        container.getController().getTaskController().stop();
        container.getController().getDatabaseController().stop();
        container.getController().getRouterController().stop();
    }
}
