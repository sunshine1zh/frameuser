package com.zhang.utils;

import lombok.SneakyThrows;

/**
 * @Author zhangtopsun@foxmail.com
 * @Description
 * @Date 2024/12/4
 */
public class BaseUtil {

    @SneakyThrows
    public static <T> T newInstance(final Class<T> targetClass) {
        T targetObj = targetClass.newInstance();
        return targetObj;
    }
}
