package com.zhang.utils;

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
public class BeanUtils {

    /*
     * object copy.
     *
     * @param origin the source bean
     * @param target the target bean
     */
    public static void copyProperties(final Object origin, final Object target) {
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

//    private static Map<String, Field> resolveOriginFieldMap(final Class cls) {
//        Map<String, Field> fieldMap = new HashMap<>(6);
//        List<Field> declaredFields =
//                ReflectionUtils.getDeclaredFields(cls).stream()
//                        .sorted(BeanHelper::comparator)
//                        .collect(Collectors.toList());
//        for (Field field : declaredFields) {
//            CopyField copyField = field.getAnnotation(CopyField.class);
//            String fieldName = field.getName();
//            if (copyField != null) {
//                fieldName =
//                        StringUtils.isNotBlank(copyField.targetField()) ? copyField.targetField() : fieldName;
//            }
//            field.setAccessible(true);
//            fieldMap.put(fieldName, field);
//        }
//        return fieldMap;
//    }

}
