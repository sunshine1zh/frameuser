package com.zhang.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangtopsun@foxmail.com
 * @Description
 * @Date 2024/10/14
 */
public class BeanUtils {

    /*
     * object copy.
     *
     * @param origin origin
     * @param target target
     */
    public static void copy(final Object origin, final Object target) {
//        AssertHelper.notNull(origin, "origin.is.null");
//        AssertHelper.notNull(target, "target.is.null");
//        List<PropertyDescriptor> targetPds =
//                CachedIntrospectionHelperResults.getPropertyDescriptors(target.getClass());
//        Map<String, Field> originFieldMap = resolveOriginFieldMap(origin.getClass());
//        for (PropertyDescriptor propertyDescriptor : targetPds) {
//            Method writeMethod = propertyDescriptor.getWriteMethod();
//            String name = propertyDescriptor.getName();
//            Field originField = originFieldMap.get(name);
//            if (originField == null || writeMethod == null) {
//                continue;
//            }
//            Class<?> type = propertyDescriptor.getPropertyType();
//            if (type.equals(originField.getType())) {
//                try {
//                    Object originValue = originField.get(origin);
//                    if (originValue != null) {
//                        writeMethod.invoke(target, originValue);
//                    }
//                } catch (IllegalAccessException e) {
//                    throw new IllegalStateException(
//                            "Not allowed to access field '" + originField.getName() + "':" + " " + e);
//                } catch (InvocationTargetException e) {
//                    throw new IllegalStateException(
//                            "Not allowed to write field '" + originField.getName() + "':" + " " + e);
//                }
//            }
//        }
    }

}
