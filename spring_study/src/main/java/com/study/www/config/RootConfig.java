package com.study.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@MapperScan(basePackages = {"com.study.www.repository"})
@ComponentScan(basePackages = {"com.study.www.service"})
@EnableTransactionManagement
@EnableScheduling
@Configuration
public class RootConfig {

	@Autowired
	ApplicationContext applicationContext;
	
	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		
//		 집에서 사용
		 hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		 hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springtest");
		 hikariConfig.setUsername("springUser"); 
		 hikariConfig.setPassword("mysql");
		 
		/*
		 * hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		 * hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/mywebdb");
		 * hikariConfig.setUsername("mywebUser"); hikariConfig.setPassword("mysql");
		 */
		
		hikariConfig.setMaximumPoolSize(5); 
		hikariConfig.setMinimumIdle(5); 
		
		hikariConfig.setConnectionTestQuery("SELECT now()"); 
		hikariConfig.setPoolName("springHikariCP");
		
		
		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "true"); //기본값으로 설정
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		
		return hikariDataSource;
		
	}
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		
		sqlFactoryBean.setDataSource(dataSource());
		sqlFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mappers/*.xml"));
		
		sqlFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/MybatisConfig.xml"));
		
		return sqlFactoryBean.getObject();
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
