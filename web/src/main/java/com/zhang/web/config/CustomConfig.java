package com.zhang.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * Get the configuration.
 * 获取配置.
 *
 * @author: zhangtopsun@foxmail.com
 * @date: 2022/11/16 14:02
 * @version: 1.0
 */
@Data
@ConfigurationProperties(prefix = CustomConfig.CONFIG_PREFIX)
@Component
public class CustomConfig {

  public static final String CONFIG_PREFIX = "custom.config";

}
