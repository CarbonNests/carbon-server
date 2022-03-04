package io.hanbings.carbon.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cache {
    // 缓存 ID
    String cid;
    // 过期时间
    long expire;
    // 缓存内容
    String content;
}
