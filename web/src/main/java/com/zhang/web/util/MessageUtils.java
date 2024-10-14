package com.zhang.web.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/*
 * @author sunny
 * @description: internationalization 国际化
 * @date 2022/11/1 11:47
 */
public class MessageUtils {

  private static MessageSource messageSource;


  static  {
    messageSource = (MessageSource) SpringContextUtils.getBean("messageSource");
  }


  public static String getMessage(int code) {
    return getMessage(code, new String[0]);
  }

  public static String getMessage(int code, String... params) {
    return messageSource.getMessage(code + "", params, LocaleContextHolder.getLocale());
  }
}
