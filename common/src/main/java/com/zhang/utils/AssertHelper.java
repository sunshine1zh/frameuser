package com.zhang.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/*
 * .
 *
 * @author: zhangtopsun@foxmail.com
 * @date: 2022/11/15 17:27
 * @version: 1.0
 */
public class AssertHelper {

  /*
   * null assert.
   *
   * @param source source
   * @param errorKey key
   */
  public static void isNull(final Object source, final String errorKey) {
    if (Objects.nonNull(source)) {
      throw new BaseException(errorKey);
    }
  }

  /*
   * true assert.
   *
   * @param source source
   * @param errorKey key
   */
  public static void isTure(final Boolean source, final String errorKey) {
    if (!source) {
      throw new BaseException(errorKey);
    }
  }

  /*
   * not blank assert.
   *
   * @param source source
   * @param errorKey key
   */
  public static void notBlank(final String source, final String errorKey) {
    if (source == null || source.isEmpty()) {
      throw new BaseException(errorKey);
    }
  }

  /*
   * not empty assert.
   *
   * @param source source
   * @param errorKey key
   */
  public static void notEmpty(final Collection<?> source, final String errorKey) {
    if (source == null || source.isEmpty()) {
      throw new BaseException(errorKey);
    }
  }

  /*
   * not empty assert.
   *
   * @param source source
   * @param errorKey key
   */
  public static void notEmpty(final Map<?, ?> source, final String errorKey) {
    if (source == null || source.isEmpty()) {
      throw new BaseException(errorKey);
    }
  }

  /*
   * not assert.
   *
   * @param source source
   * @param errorKey key
   */
  public static void notNull(final Object source, final String errorKey) {
    if (Objects.isNull(source)) {
      throw new BaseException(errorKey);
    }
  }

  /*
   * not null assert.
   *
   * @param source source
   * @param errorKey key
   */
  public static void notNull(final Collection<?> source, final String errorKey) {
    if (source == null) {
      throw new BaseException(errorKey);
    }
  }

  /*
   * not null assert.
   *
   * @param source source
   * @param errorKey key
   */
  public static void notNull(final Map<?, ?> source, final int errorKey) {
    if (source == null) {
      throw new BaseException(errorKey);
    }
  }

  /*
   * equal assert.
   *
   * @param target target
   * @param source source
   * @param errorKey key
   */
  public static void isEquals(final String target, final String source, final String errorKey) {
    isEquals(target, source, false, errorKey);
  }

  /*
   * equal assert.
   *
   * @param target target
   * @param source source
   * @param ignoreCase case
   * @param errorKey key
   */
  public static void isEquals(
      final String target, final String source, final boolean ignoreCase, final String errorKey) {
    String targetSwap = target;
    String sourceSwap = source;
    if (ignoreCase) {
      targetSwap = targetSwap.toLowerCase();
      sourceSwap = sourceSwap.toLowerCase();
    }
    if (!Objects.equals(targetSwap, sourceSwap)) {
//      log.info("value is same or not {},{},{}", target, source, errorKey);
      throw new BaseException(errorKey);
    }
  }

  /*
   * equal assert.
   *
   * @param target target
   * @param source source
   * @param errorKey key
   */
  public static void isEquals(final Object target, final Object source, final String errorKey) {
    if (!target.equals(source)) {
      throw new BaseException(errorKey);
    }
  }

  /*
   * small assert.
   *
   * @param target target
   * @param source source
   * @param errorKey key
   */
  public static void isSmall(
      final BigDecimal target, final BigDecimal source, final String errorKey) {
    if (source.compareTo(target) < 0) {
      throw new BaseException(errorKey);
    }
  }
}
