package io.hanbings.carbon.interfaces;

import io.hanbings.carbon.data.Account;
import io.hanbings.carbon.data.OAuth2Service;

public interface DatabaseService extends Service {
    // 账户
    void addAccount(Account account);

    Account getAccount(String uid);

    void updateAccount(Account account);

    void deleteAccount(String uid);

    boolean hasAccount(String email);

    // OAuth2 服务
    void addOAuth2Service(OAuth2Service oAuth2Service);

    OAuth2Service getOAuth2Service(String sid);

    void updateOAuth2Service(OAuth2Service oAuth2Service);

    void deleteOAuth2Service(String sid);
}
