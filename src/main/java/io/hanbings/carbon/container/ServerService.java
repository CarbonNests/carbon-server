package io.hanbings.carbon.container;

import io.hanbings.carbon.service.MongoDBService;
import io.hanbings.carbon.service.ResponseMessageService;
import io.hanbings.carbon.service.ThreadService;
import io.hanbings.carbon.service.VertxService;
import lombok.Data;

@Data
public class ServerService {
    ThreadService threadService;
    VertxService vertxService;
    ResponseMessageService responseMessageService;
    MongoDBService mongoDBService;
}
