package com.anubhav.springdatajpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(useDefaultFilters = false,basePackages = {"com.anubhav.springdatajpa.controllers"},
includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = org.springframework.stereotype.Controller.class))
@PropertySources(value = {@PropertySource(value = "classpath:application.properties")})
public class WebConfig implements WebMvcConfigurer{
	@Autowired
	private Environment environment;
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
	
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		
		viewResolver.setPrefix(environment.getProperty("com.anubhav.springdatajpa.mvc.views.prefix"));
		viewResolver.setSuffix(environment.getProperty("com.anubhav.springdatajpa.mvc.views.suffix"));
		return viewResolver;
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/css/,classpath:/static/js/");
	}

}
