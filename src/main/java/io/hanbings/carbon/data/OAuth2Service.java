package io.hanbings.carbon.data;

import com.google.gson.annotations.SerializedName;
import io.hanbings.carbon.common.content.AuthServicePermissionType;
import io.hanbings.carbon.common.content.AuthServiceStatusType;
import lombok.Data;

import java.util.List;

/**
 * 描述在平台注册的鉴权服务
 */
@Data
public class OAuth2Service {
    // Auth Service ID
    String sid;
    // 管理员 UID
    String owner;
    // 名称
    String name;
    // 描述
    String description;
    // 网站主页
    String page;
    // 申请权限
    List<AuthServicePermissionType> permissions;
    // 状态
    AuthServiceStatusType status;
    // OAuth Client ID
    @SerializedName("client_id")
    String clientId;
    // OAuth Client Secret
    @SerializedName("client_secret")
    String clientSecret;
    // OAuth Callback URI
    @SerializedName("callback_uri")
    String redirectUri;
}
