package io.hanbings.carbon;

import io.hanbings.carbon.common.content.Carbon;
import io.hanbings.carbon.controller.CarbonController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarbonServer {
    public static void main(String[] args) {
        new CarbonController().run();
    }
}
