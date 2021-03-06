package io.hanbings.carbon.common.config;

import com.google.gson.annotations.SerializedName;
import io.hanbings.carbon.data.OAuth2Platform;
import lombok.Data;

import java.util.List;

@Data
public class Config {

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
