package com.zhang.web.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @Author zhangtopsun@foxmail.com
 * @Description
 * @Date 2024/10/14
 */
@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        // 设置缓存秒数，默认为 -1，表示不缓存
        messageSource.setCacheSeconds(3600);
        return messageSource;
    }
}
