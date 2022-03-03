package io.hanbings.carbon.common.content;

import java.util.Arrays;

public enum OAuth2PlatformType {
    GITHUB;

    // todo: 有必要的话可以改个 UNKNOWN 枚举出来
    public static OAuth2PlatformType lookup(final String name) {
        return Arrays.stream(OAuth2PlatformType.values())
                .anyMatch(type -> type.name().equalsIgnoreCase(name))
                ? OAuth2PlatformType.valueOf(name.toUpperCase()) : null;
    }
}
