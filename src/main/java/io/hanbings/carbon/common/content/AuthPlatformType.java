package io.hanbings.carbon.common.content;

import java.util.Arrays;

public enum AuthPlatformType {
    GITHUB_OAUTH2;

    public static AuthPlatformType lookup(String name) {
        for (AuthPlatformType type : AuthPlatformType.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return AuthPlatformType.GITHUB_OAUTH2;
    }
}
