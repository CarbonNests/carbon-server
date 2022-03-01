package io.hanbings.carbon.router;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储需要在各个路由间传递的上下文信息 <br>
 * Key 为域名称 <br>
 * Value 为路由上下文 Map
 */
public class RouterContext {
    public static Map<RouterContextStatusCodeType, Object> statusToken = new HashMap<>();
}
