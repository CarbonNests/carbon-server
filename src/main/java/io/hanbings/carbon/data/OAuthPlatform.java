package io.hanbings.carbon.data;

import com.google.gson.annotations.SerializedName;
import io.hanbings.carbon.common.content.AuthPlatformType;
import lombok.Data;

/**
 * 描述可以用来登录平台的第三方鉴权服务
 */
@Data
public class OAuthPlatform {
    // 平台 类型
    AuthPlatformType platform;
    // 平台名称
    String name;
    // 描述
    String description;
    // OAuth Authentication Server
    @SerializedName("auth_server")
    String authServer;
    // OAuth Resource Server
    @SerializedName("resource_server")
    String resourceServer;
    // OAuth Client ID
    @SerializedName("client_id")
    String clientId;
    // OAuth Client Secret
    @SerializedName("client_secret")
    String clientSecret;
}
