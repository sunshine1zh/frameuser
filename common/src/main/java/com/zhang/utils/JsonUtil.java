package com.zhang.utils;

import com.alibaba.fastjson2.JSON;

import java.util.List;

/**
 * @Author zhangtopsun@foxmail.com
 * @Description
 * @Date 2024/8/7
 */
public class JsonUtil {

    public static  <T> List<T> toJsonArray(final String json, final Class<T> target) {
        return JSON.parseArray(json, target);
    }

    public static  <T> T toObject(final String json, final Class<T> target) {
        return JSON.parseObject(json, target);
    }

    public static String toJsonString(final Object origin) {
        return JSON.toJSONString(origin);
    }
}
