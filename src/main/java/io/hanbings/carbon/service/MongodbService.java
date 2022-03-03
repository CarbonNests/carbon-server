package io.hanbings.carbon.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import io.hanbings.carbon.interfaces.DatabaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class MongodbService implements DatabaseService {
    final String mongodbUrl;
    final String mongodbUser;
    final String mongodbPassword;
    ServerAddress serverAddress;
    MongoCredential credential;
    MongoClient mongoClient;

    @Override
    public void create(String database, Object data) {

    }

    @Override
    public void read(String database, Object data) {

    }

    @Override
    public void update(String database, Object data) {

    }

    @Override
    public void delete(String database, Object data) {

    }

    @Override
    public void start() {
        MongoClientURI resource = new MongoClientURI(mongodbUrl);
        String host = "127.0.0.1";
        int port = 27017;

        try {
            URI uri = new URI(mongodbUrl);
            host = uri.getHost();
            port = uri.getPort();
        } catch (URISyntaxException exception) {
            log.error("mongodb url is invalid.");
            System.exit(1);
        }

        // 参数或 url 其中一个不为空则使用参数或 url
        if ((mongodbUser == null || mongodbPassword == null)
                && (resource.getUsername() == null || resource.getPassword() == null)) {
            log.warn("No username or password provided. " +
                    "This is not secure, and it is very likely that the physical server " +
                    "can be compromised without the firewall being turned on.");
            serverAddress = new ServerAddress(host, port);
            mongoClient = new MongoClient(serverAddress, resource.getOptions());
        } else {
            serverAddress = new ServerAddress(host, port);
            // url 的用户名和密码 或 参数的用户名和密码
            // 按配置文件说明 url 的用户名和密码可不填写 故首选读取参数
            credential = MongoCredential.createCredential(
                    mongodbUser != null
                            ? mongodbUser
                            : Objects.requireNonNull(resource.getUsername()),
                    resource.getDatabase() != null
                            ? resource.getDatabase()
                            : "test",
                    mongodbPassword != null
                            ? mongodbPassword.toCharArray()
                            : Objects.requireNonNull(resource.getPassword())
            );
            mongoClient = new MongoClient(
                    serverAddress,
                    credential,
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
