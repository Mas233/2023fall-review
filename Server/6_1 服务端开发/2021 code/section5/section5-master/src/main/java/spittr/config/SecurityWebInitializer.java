package spittr.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {
//启动了DelegatingFilterProxy ，是一个Filter的实现类，拦截所有请求

//@Override
//public void onStartup(ServletContext servletContext) throws ServletException {
//    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//    ctx.register(MyMvcConfig.class); //注册自己的配置文件
//    ctx.setServletContext(servletContext); // 2 把 ServletContext 注入到spring容器中
//    Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx)); // 3 加载  dispatcher Servlet。
//    servlet.addMapping("/");
//    servlet.setLoadOnStartup(1);
//}
}
