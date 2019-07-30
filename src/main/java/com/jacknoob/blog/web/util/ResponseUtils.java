package com.jacknoob.blog.web.util;

import com.jacknoob.blog.common.Constants;

import java.util.Map;

/**
 * @author JackJun
 * 2019/7/30 14:19
 * Life is just about survival.
 */
public class ResponseUtils {
    /**
     * 规范数据装配
     *
     * @param refMap 视图模型对象
     */
    public static void assemblyRefMap(final Map<String, Object> refMap, Object data) {
        refMap.put(Constants.ResponseProp.DATA.getPropName(), data);
    }

    public static void assemblyRefMap(final Map<String, Object> refMap, Object data, Object page) {
        refMap.put(Constants.ResponseProp.DATA.getPropName(), data);
        refMap.put(Constants.ResponseProp.PAGE.getPropName(), page);
    }
}
