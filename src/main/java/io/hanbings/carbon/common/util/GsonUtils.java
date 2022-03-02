package io.hanbings.carbon.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.hanbings.carbon.common.content.AccountPermissionType;
import io.hanbings.carbon.common.content.AccountPermissionTypeAdapter;
import io.hanbings.carbon.common.content.AccountStatusType;
import io.hanbings.carbon.common.content.AccountStatusTypeAdapter;
import io.hanbings.carbon.common.content.AuthPlatformType;
import io.hanbings.carbon.common.content.AuthPlatformTypeAdapter;
import io.hanbings.carbon.common.content.AuthServicePermissionType;
import io.hanbings.carbon.common.content.AuthServicePermissionTypeAdapter;
import io.hanbings.carbon.common.content.AuthServiceStatusType;
import io.hanbings.carbon.common.content.AuthServiceStatusTypeAdapter;

public class GsonUtils {
    public static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(AccountPermissionType.class, new AccountPermissionTypeAdapter())
                .registerTypeAdapter(AccountStatusType.class, new AccountStatusTypeAdapter())
                .registerTypeAdapter(AuthPlatformType.class, new AuthPlatformTypeAdapter())
                .registerTypeAdapter(AuthServicePermissionType.class, new AuthServicePermissionTypeAdapter())
                .registerTypeAdapter(AuthServiceStatusType.class, new AuthServiceStatusTypeAdapter())
                .disableHtmlEscaping()
                .create();
    }
}
