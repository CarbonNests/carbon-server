package io.hanbings.carbon.common.util;

public class TimeUtils {
    public static long getSessionCacheExpireTime() {
        return System.currentTimeMillis() + 300000L;
    }

}
