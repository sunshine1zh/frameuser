package com.zhang.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.CachedIntrospectionResults;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author zhangtopsun@foxmail.com
 * @Description
 * @Date 2024/10/14
 */
public class BeanHelper {

    /**
     * list target object copy.
     *
     * @param origins origins
     * @param targetClass targetClass
     * @param <O> o
     * @param <T> t
     * @return targetObjects
     */
    public static <O, T> List<T> copyList(final List<O> origins, final Class<T> targetClass) {
        AssertHelper.notNull(origins, "source.must.not.be.null");
        return origins.stream()
                .map(
                        o -> {
                            T t = BaseUtil.newInstance(targetClass);
                            BeanUtils.copyProperties(o, t);
                            return t;
                        })
                .collect(Collectors.toList());
    }


}
