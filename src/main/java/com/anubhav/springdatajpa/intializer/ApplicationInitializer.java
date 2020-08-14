package com.anubhav.springdatajpa.intializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.anubhav.springdatajpa.config.RootConfig;
import com.anubhav.springdatajpa.config.WebConfig;

public class ApplicationInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext=new AnnotationConfigWebApplicationContext();
		rootContext.register(RootConfig.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		AnnotationConfigWebApplicationContext mvcContext=new AnnotationConfigWebApplicationContext();
		mvcContext.register(WebConfig.class);
		
		DispatcherServlet dispatcherServlet=new DispatcherServlet(mvcContext);
		ServletRegistration.Dynamic servlet=servletContext.addServlet("dispatcherServlet", dispatcherServlet);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		servlet.addMapping("/");
		servlet.setLoadOnStartup(0);
		
		
	}

}
