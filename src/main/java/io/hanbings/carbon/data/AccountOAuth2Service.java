package io.hanbings.carbon.data;

import io.hanbings.carbon.common.content.AuthServicePermissionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 本账号授权给本站鉴权服务的信息
 */
@Data
@AllArgsConstructor
public class AccountOAuth2Service {
    // 授权的服务 Id
    String sid;
    // 允许的权限
    List<AuthServicePermissionType> permissions;
}
