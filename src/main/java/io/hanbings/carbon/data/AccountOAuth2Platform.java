package io.hanbings.carbon.data;

import io.hanbings.carbon.common.content.OAuth2PlatformType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 账户使用第三方平台登录帐号后绑定到账号的信息
 */
@Data
@AllArgsConstructor
public class AccountOAuth2Platform {
    // 平台的 类型
    OAuth2PlatformType platform;
    // token
    Token token;
}
