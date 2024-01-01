package com.example.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MyWebAppInitializer implements WebApplicationInitializer {

    /**
     * Servlet容器启动时会自动运行该方法
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        servletContext.setInitParameter("contextConfigLocation", "classpath:applicationContext.xml");

        ServletRegistration.Dynamic registration = servletContext.addServlet("viewspace", new DispatcherServlet());
        registration.setLoadOnStartup(3);
        registration.addMapping("*.html");

        servletContext.addListener(new ContextLoaderListener());
    }
}
