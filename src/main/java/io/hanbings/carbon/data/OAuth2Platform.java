package io.hanbings.carbon.data;

import com.google.gson.annotations.SerializedName;
import io.hanbings.carbon.common.content.OAuth2PlatformType;
import lombok.Data;

/**
 * 描述可以用来登录平台的第三方鉴权服务
 */
@Data
public class OAuth2Platform {
    // 平台 类型
    OAuth2PlatformType platform;
    // 平台名称
    String name;
    // 描述
    String description;
    // OAuth Authentication Server
    @SerializedName("auth_server")
    String authServer;
    // OAuth Access Token Request
    @SerializedName("access_request_server")
    String accessRequestServer;
    // OAuth Resource Server
    @SerializedName("resource_server")
    String resourceServer;
    // OAuth Client ID
    @SerializedName("client_id")
    String clientId;
    // OAuth Client Secret
    @SerializedName("client_secret")
    String clientSecret;
    @SerializedName("redirect_uri")
    String redirectUri;
    // OAuth Request Permission
    String permission;
}
