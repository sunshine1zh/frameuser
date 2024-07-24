package com.zhang.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/*
 * Database parameters.
 * 数据库参数.
 *
 * @author: zhanghq@yxt.com
 * @date: 2022/11/10 9:33
 * @version: 1.0
 */
@ConfigurationProperties(prefix = HikariDataSourceConfig.HIKARI_DATASOURCE_PREFIX)
@Getter
@Setter
public class HikariDataSourceConfig {

  public static final String HIKARI_DATASOURCE_PREFIX = "custom.datasource.mysql";

  private String url;

  private String username;

  private String password;

  private String driverClassName;

  private int maximumPoolSize = 200;

  private int idleTimeout = 60000;

  private int maxLifetime = 18000;

  private int connectionTimeout = 11000;

  private String connectionTestQuery;
}
