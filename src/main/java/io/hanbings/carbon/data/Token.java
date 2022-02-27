package io.hanbings.carbon.data;

import lombok.Data;

/**
 * 帐号拥有的 Token 对象 <br>
 * 包括第三方登录的 Token 和系统授权鉴权服务的 Token <br>
 * 包含 token 和 token 的过期时间
 */
@Data
public class Token {
    // token
    String token;
    // token 过期时间
    String expire;
}
