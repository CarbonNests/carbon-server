package io.hanbings.carbon.common.content;

public enum AuthPlatformType {
    GITHUB_OAUTH2;

    // 忽略大小写查找
    public static AuthPlatformType lookup(String name) {
        for (AuthPlatformType type : AuthPlatformType.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return AuthPlatformType.GITHUB_OAUTH2;
    }
}
