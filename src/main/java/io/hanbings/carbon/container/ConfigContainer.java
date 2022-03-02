package io.hanbings.carbon.container;

import com.google.gson.annotations.SerializedName;
import io.hanbings.carbon.data.OAuth2Platform;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class ConfigContainer {
    @SerializedName("is_debug")
    boolean isDebug;
    @SerializedName("server_port")
    int serverPort;
    @SerializedName("thread_core_pool_size")
    int threadCorePoolSize;
    @SerializedName("thread_max_pool_size")
    int threadMaxPoolSize;
    @SerializedName("thread_keep_alive_seconds")
    long threadKeepAliveTime;
    @SerializedName("mongodb_url")
    String mongodbUrl;
    @SerializedName("mongodb_user")
    String mongodbUser;
    @SerializedName("mongodb_password")
    String mongodbPassword;
    @SerializedName("oauth2_platform")

    List<OAuth2Platform> oAuth2Platforms;
}
