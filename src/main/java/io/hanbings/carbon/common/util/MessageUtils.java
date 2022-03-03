package io.hanbings.carbon.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.hanbings.carbon.common.content.AccountPermissionType;
import io.hanbings.carbon.common.content.AccountPermissionTypeAdapter;
import io.hanbings.carbon.common.content.AccountStatusType;
import io.hanbings.carbon.common.content.AccountStatusTypeAdapter;
import io.hanbings.carbon.common.content.AuthServicePermissionType;
import io.hanbings.carbon.common.content.AuthServicePermissionTypeAdapter;
import io.hanbings.carbon.common.content.AuthServiceStatusType;
import io.hanbings.carbon.common.content.AuthServiceStatusTypeAdapter;
import io.hanbings.carbon.common.content.OAuth2PlatformType;
import io.hanbings.carbon.common.content.OAuth2PlatformTypeAdapter;

public class MessageUtils {
    static GsonBuilder builder = new GsonBuilder()
            .registerTypeAdapter(AccountPermissionType.class, new AccountPermissionTypeAdapter())
            .registerTypeAdapter(AccountStatusType.class, new AccountStatusTypeAdapter())
            .registerTypeAdapter(OAuth2PlatformType.class, new OAuth2PlatformTypeAdapter())
            .registerTypeAdapter(AuthServicePermissionType.class, new AuthServicePermissionTypeAdapter())
            .registerTypeAdapter(AuthServiceStatusType.class, new AuthServiceStatusTypeAdapter())
            .disableHtmlEscaping();
    static Gson gson = builder.create();
}
