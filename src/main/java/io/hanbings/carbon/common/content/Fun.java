package io.hanbings.carbon.common.content;

import java.util.Arrays;
import java.util.List;

public class Fun {
    static List<String> fun = Arrays.asList(
            "吃掉你的小蛋糕",
            "帮小猫咪洗澡",
            "和宝可梦开始一场新的旅程",
            "请你吃一顿美味的海鲜大餐",
            "打扫你的房间",
            "喵呜~喵呜~喵呜~",
            "春眠不觉晓，有些小烦恼"
    );

    // 提供一个随机的功能
    public static String fun() {
        int index = (int) (Math.random() * fun.size());
        return fun.get(index);
    }
}
