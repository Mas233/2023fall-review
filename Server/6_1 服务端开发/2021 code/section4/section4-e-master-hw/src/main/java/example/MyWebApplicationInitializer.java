package example;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @Author stormbroken
 * Create by 2021/03/12
 * @Version 1.0
 **/

public class MyWebApplicationInitializer implements WebApplicationInitializer{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        // 把 ServletContext 注入到spring容器中
        ctx.setServletContext(servletContext);
        ServletRegistration.Dynamic techlogServlet1 =
                servletContext.addServlet("hello1", MyServlet1.class);
        techlogServlet1.setLoadOnStartup(1);
        techlogServlet1.addMapping("/s1");
        ServletRegistration.Dynamic techlogServlet2 =
                servletContext.addServlet("hello2", MyServlet2.class);
        techlogServlet2.setLoadOnStartup(1);
        techlogServlet2.addMapping("/s2");
        ServletRegistration.Dynamic techlogServlet3 =
                servletContext.addServlet("hello3", MyServlet3.class);
        techlogServlet3.setLoadOnStartup(1);
        techlogServlet3.addMapping("/s3");

        FilterRegistration.Dynamic filter = servletContext.addFilter(
                "MyFilter", MyFilter.class);
        filter.addMappingForUrlPatterns(null, false, "/*");
    }
}
