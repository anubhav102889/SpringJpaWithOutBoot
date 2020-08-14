package com.anubhav.springdatajpa.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.util.BeanDefinitionUtils.EntityManagerFactoryBeanDefinition;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.anubhav.springdatajpa.repositories"},
entityManagerFactoryRef = "entityManagerFactoryBean",transactionManagerRef = "transactionManager")
@ComponentScan(useDefaultFilters = true, basePackages = {
		"com.anubhav.springdatajpa.services","com.anubhav.springdatajpa.repositories" }, excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.stereotype.Controller.class))
@PropertySources(value = {@PropertySource(value = "classpath:application.properties")})
public class RootConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public DataSource dataSource () {
		HikariDataSource dataSource=new HikariDataSource();
		dataSource.setDriverClassName(environment.getProperty("com.anubhav.springdatajpa.database.driverClassName"));
		dataSource.setJdbcUrl(environment.getProperty("com.anubhav.springdatajpa.database.jdbcUrl"));
		dataSource.setUsername(environment.getProperty("com.anubhav.springdatajpa.database.username"));
		dataSource.setPassword(environment.getProperty("com.anubhav.springdatajpa.database.password"));
		return dataSource;
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean=new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.anubhav.springdatajpa.entities");
		Map<String,Object> propertyMap=new HashMap<>();
		propertyMap.put("hibernate.hbm2ddl.auto", "update");
		propertyMap.put("hibernate.dialect", org.hibernate.dialect.MySQL5Dialect.class);
		propertyMap.put("hibernate.show_sql", "true");
		propertyMap.put("hibernate.format_sql", "true");
		propertyMap.put("hibernate.implicit_naming_strategy", org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl.class);
		propertyMap.put("hibernate.physical_naming_strategy", org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl.class);
		localContainerEntityManagerFactoryBean.setJpaPropertyMap(propertyMap);
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return localContainerEntityManagerFactoryBean;
				
	}
	
	@Bean
	JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		JpaTransactionManager transactionManager=new JpaTransactionManager();
		transactionManager.setEntityManagerFactory( entityManagerFactoryBean.getObject());
		return transactionManager;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
