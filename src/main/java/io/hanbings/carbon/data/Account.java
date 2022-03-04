package io.hanbings.carbon.data;

import com.google.gson.annotations.SerializedName;
import io.hanbings.carbon.common.content.AccountPermissionType;
import io.hanbings.carbon.common.content.AccountStatusType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Account {
    // 用户唯一 ID
    String uid;
    // 用户名
    String username;
    // 昵称
    String nickname;
    // 用户邮箱
    String email;
    // 用户头像 url
    String avatar;
    // 权限列表
    List<AccountPermissionType> permissions;
    // 账号状态
    AccountStatusType status;
    // 创建时间
    long createTime;
    // 更新时间
    long updateTime;

    // 鉴权服务 第三方 和授权到外部
    // 可用于登录帐号的 OAuth 平台授权
    @SerializedName("oauth2_platform")
    List<AccountOAuth2Platform> oAuthPlatforms;
    // 授权的 OAuth 服务
    @SerializedName("oauth2_services")
    List<AccountOAuth2Service> oAuthServices;
}
