package com.zhang.web.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Order(0)
@Configuration
@EnableConfigurationProperties(HikariDataSourceConfig.class)
@MapperScan(
    basePackages = {"com.zhang.mysql"},
    sqlSessionFactoryRef = "sqlSessionFactory")
@Slf4j
public class DataSourceConfigurations {

  /*
   * datasource.
   *
   * @param hikariDataSourceProperties proerpties
   * @return datasource
   */

  /**
   *
   * @param hikariDataSourceProperties
   * @return
   */
  @Bean("dataSource")
  public DataSource getHikariDataSource(final HikariDataSourceConfig hikariDataSourceProperties) {
    HikariDataSource hikariDataSource = new HikariDataSource();
    hikariDataSource.setJdbcUrl(hikariDataSourceProperties.getUrl());
    hikariDataSource.setUsername(hikariDataSourceProperties.getUsername());
    hikariDataSource.setPassword(hikariDataSourceProperties.getPassword());
    /*
     * The maximum number of connections allowed in the connection pool. Default value: 10. Recommended formula: (core_count * 2) + effective_spindle_count) -
     * 连接池中允许的最大连接数。缺省值：10；推荐的公式：((core_count * 2) + effective_spindle_count) -
     */
    hikariDataSource.setMaximumPoolSize(hikariDataSourceProperties.getMaximumPoolSize());
    /*
     * Maximum duration (ms) for which a connection is idle. If the connection expires, it will be retired. The default value is 10 minutes
     *一个连接idle状态的最大时长（毫秒），超时则被释放（retired），缺省:10分钟 --
     */
    hikariDataSource.setIdleTimeout(hikariDataSourceProperties.getIdleTimeout());
    /*
     * The lifetime of a connection (milliseconds), times out and is retired if not used,
     * The default value is 30 minutes. You are advised to set it to 30 seconds less than the database timeout period.
     * Refer to the MySQL wait_timeout parameter (show variables like '%timeout%';)
     *  一个连接的生命时长（毫秒），超时而且没被使用则被释放（retired），
     *  缺省:30分钟，建议设置比数据库超时时长少30秒，
     *  参考MySQL wait_timeout参数（show variables like '%timeout%';）
     */
    hikariDataSource.setMaxLifetime(hikariDataSourceProperties.getMaxLifetime());
    hikariDataSource.setConnectionTimeout(hikariDataSourceProperties.getConnectionTimeout());
    hikariDataSource.setDriverClassName(hikariDataSourceProperties.getDriverClassName());
    hikariDataSource.setConnectionTestQuery(hikariDataSourceProperties.getConnectionTestQuery());
    return hikariDataSource;
  }

//  @Primary
//  @Bean(name = "transactionManager")
//  public DataSourceTransactionManager transactionManager(
//      final @Qualifier("dataSource") DataSource dataSource) {
//    return new DataSourceTransactionManager(dataSource);
//  }

  /*
   * sqlSessionFactory.
   *
   * @param dataSource datasource
   * @return sqlSessionFactory
   * @throws Exception exception
   */
  @Primary
  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(final @Qualifier("dataSource") DataSource dataSource)
      throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
//    TypeHandler[] typeHandlers = new TypeHandler[] {new TimeStampToLocalDateConverter()};
//    factoryBean.setTypeHandlers(typeHandlers);
    String path = "classpath*:mapper/master/*.xml";
    factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(path));
    org.apache.ibatis.session.Configuration configuration =
        new org.apache.ibatis.session.Configuration();
    configuration.setMapUnderscoreToCamelCase(true);
    factoryBean.setConfiguration(configuration);
    SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
    return sqlSessionFactory;
  }
}
