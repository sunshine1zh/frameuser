package com.zhang.web.config;

import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Get the configuration.
 * 获取配置.
 *
 * @author: zhangtopsun@foxmail.com
 * @date: 2022/11/16 14:02
 * @version: 1.0
 */

/**
 *
 */
@ConfigurationProperties(prefix = CustomConfig.CONFIG_PREFIX)
@Component
public class CustomConfig {
  public static final String CONFIG_PREFIX = "custom.config";

}
