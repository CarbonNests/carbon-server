package io.hanbings.carbon.common.util;

/**
 * 生成 uuid 工具类 <br>
 * 什么时候能用出重复了再改 （捂嘴笑）
 */
public class UuidUtils {
    public static String getUuid() {
        return java.util.UUID.randomUUID().toString();
    }
}
