package io.hanbings.carbon.container;

import lombok.Data;

@Data
public class ServerDependencyContainer {
    ServerConfig config;
    ServerService service;
    ServerController controller;
}
