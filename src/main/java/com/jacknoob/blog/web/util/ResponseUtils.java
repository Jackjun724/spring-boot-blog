package com.jacknoob.blog.web.util;

import com.jacknoob.blog.common.Constants;
import com.jacknoob.blog.web.response.Page;

import java.util.List;
import java.util.Map;

/**
 * @author JackJun
 * 2019/7/30 14:19
 * Life is just about survival.
 */
public class ResponseUtils {
    /**
     * 规范数据装配
     * @param refMap 视图模型对象
     */
    public static void assemblyRefMap(final Map<String, Object> refMap, Object data) {
        refMap.put(Constants.ResponseProp.DATA.getPropName(), data);
    }

    /**
     * 装配分页数据
     * @return 分页数据
     */
    public static <T> Page<T> assemblyPage(final Page<T> page, List<T> data) {
        page.setData(data);
        return page;
    }

    /**
     * PageHelper page 对象转换为 简单的Response对象
     *
     * @param page pageHelper Entity
     * @param <T>  DataType
     * @return 简单的Response对象
     */
    public static <T> Page<T> pageHelperEntity2RespPage(com.github.pagehelper.Page<T> page) {
        Page<T> res = new Page<>();
        res.setData(page.getResult());
        res.setPage(page.getPageNum());
        res.setPageSize(page.getPageSize());
        res.setTotal(page.getTotal());
        return res;
    }
}
