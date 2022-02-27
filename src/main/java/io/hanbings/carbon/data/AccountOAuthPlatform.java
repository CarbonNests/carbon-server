package io.hanbings.carbon.data;

import io.hanbings.carbon.common.content.AuthPlatformType;
import lombok.Data;

/**
 * 账户使用第三方平台登录帐号后绑定到账号的信息
 */
@Data
public class AccountOAuthPlatform {
    // 平台的 类型
    AuthPlatformType platform;
    // token
    Token token;
}
