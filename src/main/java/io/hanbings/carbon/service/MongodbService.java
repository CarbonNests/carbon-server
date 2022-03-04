package io.hanbings.carbon.service;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import io.hanbings.carbon.common.util.GsonUtils;
import io.hanbings.carbon.data.Account;
import io.hanbings.carbon.data.OAuth2Service;
import io.hanbings.carbon.interfaces.DatabaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

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
    String database;
    Gson gson = GsonUtils.getGson();


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
                            : "carbon",
                    mongodbPassword != null
                            ? mongodbPassword.toCharArray()
                            : Objects.requireNonNull(resource.getPassword())
            );
            mongoClient = new MongoClient(
                    serverAddress,
                    credential,
                    resource.getOptions()
            );
            // 保存数据库位置
            database = resource.getDatabase();
        }

        log.info("database service started.");
    }

    @Override
    public void stop() {
        mongoClient.close();
        log.info("database service stopped.");
    }

    @Override
    public void addAccount(Account account) {
        String json = gson.toJson(account);
        Document document = Document.parse(json);
        mongoClient.getDatabase(database).getCollection("account").insertOne(document);
    }

    @Override
    public Account getAccount(String uid) {
        Document document = mongoClient.getDatabase(database).getCollection("account")
                .find(new Document("uid", uid)).first();
        return document != null ? gson.fromJson(document.toJson(), Account.class) : null;
    }

    @Override
    public void updateAccount(Account account) {
        String json = gson.toJson(account);
        Document document = Document.parse(json);
        mongoClient.getDatabase(database).getCollection("account")
                .replaceOne(new Document("uid", account.getUid()), document);
    }

    @Override
    public void deleteAccount(String uid) {
        mongoClient.getDatabase(database).getCollection("account")
                .deleteOne(new Document("uid", uid));
    }

    @Override
    public boolean hasAccount(String email) {
        return mongoClient.getDatabase(database).getCollection("account")
                .find(new Document("email", email)).first() != null;
    }

    @Override
    public void addOAuth2Service(OAuth2Service oAuth2Service) {
        String json = gson.toJson(oAuth2Service);
        Document document = Document.parse(json);
        mongoClient.getDatabase(database).getCollection("oauth2_service").insertOne(document);
    }

    @Override
    public OAuth2Service getOAuth2Service(String sid) {
        Document document = mongoClient.getDatabase(database).getCollection("oauth2_service")
                .find(new Document("sid", sid)).first();
        return document != null ? gson.fromJson(document.toJson(), OAuth2Service.class) : null;
    }

    @Override
    public void updateOAuth2Service(OAuth2Service oAuth2Service) {
        String json = gson.toJson(oAuth2Service);
        Document document = Document.parse(json);
        mongoClient.getDatabase(database).getCollection("oauth2_service")
                .replaceOne(new Document("sid", oAuth2Service.getSid()), document);
    }

    @Override
    public void deleteOAuth2Service(String sid) {
        mongoClient.getDatabase(database).getCollection("oauth2_service")
                .deleteOne(new Document("sid", sid));
    }
}
