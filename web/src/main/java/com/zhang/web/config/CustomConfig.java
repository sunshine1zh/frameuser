package com.zhang.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


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

  private String username;
  
  private String password;
  
  public String getUsername() {
    return username;
  }
  
  public void setUsername(final String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(final String password) {
    this.password = password;
  }
}
