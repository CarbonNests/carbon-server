package io.hanbings.carbon.common.content;

public class Message {
    static public class Status {
        // 200
        public static final String SUCCESS = "请求成功 (๑>؂<๑）";
        // 403
        public static final String NOT_PERMISSION = "没有权限~ ’(°ー°〃)";
        // 404
        public static final String NOT_FOUND = "找不到页面~ Σ( ° △ °|||)︴";
        // 405
        public static final String NOT_ALLOW_OR_NOT_SUPPORT = "这里没有东西~ Σ(っ °Д °;)っ";
        // 412
        public static final String MISSING_PARAMETERS = "缺失参数 ,,Ծ^Ծ,,";
        // 500 / 501 / 502 / 503
        public static final String UNKNOWN_ERROR = "未知错误~ ヽ(*。>Д<)o゜";
        public static final String SERVER_ERROR = "服务器错误~ (｡ŏ﹏ŏ)";
    }

    static public class AccountApi {
        public static final String NOT_LOGIN = "请先登录~ ヾ(≧▽≦*)o";
        public static final String LOGIN_SUCCESS = "登录成功~ ヾ(^▽^*)))";
        public static final String LOGIN_OUT = "退出成功~ (๑>ლ<๑)";
        // 用于登录OAuth
        public static final String INVALID_OAUTH2_TOKEN = "无效的 oauth2 token！(๑ò︵ò๑)";
    }
}

