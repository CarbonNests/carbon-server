package io.hanbings.carbon.container;

import io.hanbings.carbon.controller.CarbonController;
import io.hanbings.carbon.controller.CommandController;
import io.hanbings.carbon.controller.ConfigController;
import io.hanbings.carbon.controller.RouterController;
import io.hanbings.carbon.controller.TaskController;
import lombok.Data;

@Data
public class ServerManager {
    CarbonController carbonController;
    ConfigController configController;
    TaskController taskController;
    RouterController routerController;
    CommandController commandController;
}
