package com.zhang.utils;


import lombok.extern.slf4j.Slf4j;

import java.beans.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * .
 *
 * @author: zhangtopsun@foxmail.com
 * @date: 2022/12/29 11:22
 * @version: 1.0
 */
@Slf4j
public final class CachedIntrospectionHelperResults {

  private static final Map<Class<?>, CachedIntrospectionHelperResults>
      CLASS_CACHED_INTROSPECTION_HELPER_RESULTS_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>(10);

  private final Map<String, PropertyDescriptor> propertyDescriptorCache =
      new ConcurrentHashMap<>(10);

  private CachedIntrospectionHelperResults(final Class<?> beanClass) {
    try {
      BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
      PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
      for (PropertyDescriptor pd : pds) {
        if (Class.class == beanClass
                && ("classLoader".equals(pd.getName()) || "protectionDomain".equals(pd.getName()))
            || "class".equals(pd.getName())) {
          continue;
        }
        this.propertyDescriptorCache.put(pd.getName(), pd);
      }
      test(beanInfo);
    } catch (IntrospectionException e) {
      log.error("{}", e);
    }
  }

  /*
   * cache result.
   *
   * @param beanClass beanclass
   * @return result
   */
  public static CachedIntrospectionHelperResults forClass(final Class<?> beanClass) {
    CachedIntrospectionHelperResults results =
        CLASS_CACHED_INTROSPECTION_HELPER_RESULTS_CONCURRENT_HASH_MAP.get(beanClass);
    if (results != null) {
      return results;
    }
    results = new CachedIntrospectionHelperResults(beanClass);
    CLASS_CACHED_INTROSPECTION_HELPER_RESULTS_CONCURRENT_HASH_MAP.putIfAbsent(beanClass, results);
    return results;
  }

  private void test(BeanInfo beanInfo) {
    MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
    if (methodDescriptors != null) {
      for (Method method : findCandidateWriteMethods(methodDescriptors)) {
        try {
          handleCandidateWriteMethod(method);
        } catch (IntrospectionException ex) {
          // We're only trying to find candidates, can easily ignore extra ones here...
          if (log.isDebugEnabled()) {
            log.debug("Ignoring candidate write method [" + method + "]: " + ex.getMessage());
          }
        }
      }
    }
  }

  private List<Method> findCandidateWriteMethods(MethodDescriptor[] methodDescriptors) {
    List<Method> matches = new ArrayList<>();
    for (MethodDescriptor methodDescriptor : methodDescriptors) {
      Method method = methodDescriptor.getMethod();
      if (isCandidateWriteMethod(method)) {
        matches.add(method);
      }
    }
    // Sort non-void returning write methods to guard against the ill effects of
    // non-deterministic sorting of methods returned from Class#getDeclaredMethods
    // under JDK 7. See https://bugs.java.com/view_bug.do?bug_id=7023180
    matches.sort((m1, m2) -> m2.toString().compareTo(m1.toString()));
    return matches;
  }

  public static boolean isCandidateWriteMethod(Method method) {
    String methodName = method.getName();
    int nParams = method.getParameterCount();
    return (methodName.length() > 3
        && methodName.startsWith("set")
        && Modifier.isPublic(method.getModifiers())
        && (!void.class.isAssignableFrom(method.getReturnType())
            || Modifier.isStatic(method.getModifiers()))
        && (nParams == 1 || (nParams == 2 && int.class == method.getParameterTypes()[0])));
  }

  private void handleCandidateWriteMethod(Method method) throws IntrospectionException {
    int nParams = method.getParameterCount();
    String propertyName = Introspector.decapitalize(method.getName().substring(3));
    PropertyDescriptor existingPd = getPropertyDescriptor(propertyName);
    if (nParams == 1) {
      existingPd.setWriteMethod(method);
    } else if (nParams == 2) {
      if (existingPd instanceof IndexedPropertyDescriptor) {
        ((IndexedPropertyDescriptor) existingPd).setIndexedWriteMethod(method);
      }
    } else {
      throw new IllegalArgumentException(
          "Write method must have exactly 1 or 2 parameters: " + method);
    }
  }

  /*
   * get PropertyDescriptor list.
   *
   * @param beanClass beanclass
   * @return result PropertyDescriptor
   */
  public static List<PropertyDescriptor> getPropertyDescriptors(final Class<?> beanClass) {
    CachedIntrospectionHelperResults cachedIntrospectionHelperResults =
        CachedIntrospectionHelperResults.forClass(beanClass);
    return cachedIntrospectionHelperResults.getPropertyDescriptors();
  }

  private List<PropertyDescriptor> getPropertyDescriptors() {
    List<PropertyDescriptor> propertyDescriptors = new ArrayList<>(10);
    this.propertyDescriptorCache.forEach((k, v) -> propertyDescriptors.add(v));
    return propertyDescriptors;
  }

  /*
   * get PropertyDescriptor.
   *
   * @param beanClass beanclass
   * @param name name
   * @return result PropertyDescriptor
   */
  public static PropertyDescriptor getPropertyDescriptor(
      final Class<?> beanClass, final String name) {
    CachedIntrospectionHelperResults cachedIntrospectionHelperResults =
        CachedIntrospectionHelperResults.forClass(beanClass);
    return cachedIntrospectionHelperResults.getPropertyDescriptor(name);
  }

  private PropertyDescriptor getPropertyDescriptor(final String name) {
    return this.propertyDescriptorCache.get(name);
  }
}
