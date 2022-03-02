package io.hanbings.carbon.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import io.hanbings.carbon.container.ConfigContainer;
import io.hanbings.carbon.container.ServiceContainer;
import io.hanbings.carbon.database.interfaces.DatabaseService;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Slf4j
@SuppressWarnings("SpellCheckingInspection")
public class MongoDBService implements DatabaseService {
    ServiceContainer container;
    ServerAddress serverAddress;
    MongoCredential credential;
    MongoClient mongoClient;

    public MongoDBService(ServiceContainer container) {
        this.container = container;
    }

    @Override
    public void create(Object object) {

    }

    @Override
    public void update(Object object) {

    }

    @Override
    public <T> T read(Class<T> clazz, Object id) {
        return null;
    }

    @Override
    public void delete(Object object) {

    }

    @Override
    public void start() {
        ConfigContainer config = container.getConfigService().getConfigContainer();

        MongoClientURI resource = new MongoClientURI(config.getMongodbUrl());
        String host = "127.0.0.1";
        int port = 27017;

        try {
            URI uri = new URI(config.getMongodbUrl());
            host = uri.getHost();
            port = uri.getPort();
        } catch (URISyntaxException exception) {
            log.error("mongodb url is invalid.");
            System.exit(1);
        }

        // 参数或 url 其中一个不为空则使用参数或 url
        if ((config.getMongodbUser() == null || config.getMongodbPassword() == null)
                && (resource.getUsername() == null || resource.getPassword() == null)) {
            log.warn("No username or password provided. " +
                    "This is not secure, and it is very likely that the physical server " +
                    "can be compromised without the firewall being turned on.");
            mongoClient = new MongoClient(new ServerAddress(host, port), resource.getOptions());
        } else {
            mongoClient = new MongoClient(
                    new ServerAddress(host, port),
                    // url 的用户名和密码 或 参数的用户名和密码
                    // 按配置文件说明 url 的用户名和密码可不填写 故首选读取参数
                    MongoCredential.createCredential(
                            config.getMongodbUser() != null
                                    ? config.getMongodbUser()
                                    : Objects.requireNonNull(resource.getUsername()),
                            resource.getDatabase() != null
                                    ? resource.getDatabase()
                                    : "test",
                            config.getMongodbPassword() != null
                                    ? config.getMongodbPassword().toCharArray()
                                    : Objects.requireNonNull(resource.getPassword())
                    ),
                    resource.getOptions()
            );
        }

        log.info("database service started.");
    }

    @Override
    public void stop() {
        log.info("database service stopped.");
    }
}
