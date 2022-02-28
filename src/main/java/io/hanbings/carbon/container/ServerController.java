package io.hanbings.carbon.container;

import io.hanbings.carbon.controller.CarbonController;
import io.hanbings.carbon.controller.DatabaseController;
import io.hanbings.carbon.controller.RouterController;
import io.hanbings.carbon.controller.TaskController;
import lombok.Data;

@Data
public class ServerController {
    CarbonController carbonController;
    DatabaseController databaseController;
    RouterController routerController;
    TaskController taskController;
}
