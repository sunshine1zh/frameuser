package com.zhang.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author elena
 * @Date 2022/11/17 9:24
 * @Description: 解决跨域问题
 */

@Configuration
public class CorsConfiguration {
  private org.springframework.web.cors.CorsConfiguration buildConfig() {
    org.springframework.web.cors.CorsConfiguration corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
    // sessionid Consistent with multiple sessions
    corsConfiguration.setAllowCredentials(true);

    // The domain name of the client that is allowed to access
    // 允许访问的客户端域名
    List<String> allowedOriginPatterns = new ArrayList<>();
    allowedOriginPatterns.add("*.kwynt.chintec.net");
    allowedOriginPatterns.add("*.kwynt.com");
    corsConfiguration.setAllowedOriginPatterns(allowedOriginPatterns);
    //        corsConfiguration.addAllowedOrigin("*"); // Any domain name is allowed 允许任何域名使用
    corsConfiguration.addAllowedHeader("*"); // Allow any head 允许任何头
    corsConfiguration.addAllowedMethod("*"); // Allow any method (post, get, etc.)
    return corsConfiguration;
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    // Configure cross-domain Settings for an interface 对接口配置跨域设置
    source.registerCorsConfiguration("/**", buildConfig());
    return new CorsFilter(source);
  }
}
