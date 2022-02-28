package io.hanbings.carbon.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ServerConfig {
    @SerializedName("server_port")
    int serverPort;
    @SerializedName("thread_core_pool_size")
    int threadCorePoolSize;
    @SerializedName("thread_max_pool_size")
    int threadMaxPoolSize;
    @SerializedName("thread_keep_alive_seconds")
    long threadKeepAliveTime;
    @SerializedName("oauth2_platform")
    List<OAuth2Platform> oAuth2Platforms;
}
