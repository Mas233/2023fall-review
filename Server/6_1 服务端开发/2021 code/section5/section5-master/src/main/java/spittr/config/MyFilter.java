package spittr.config;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
        if (cookies == null) {
            System.out.println("===================cookies is null======");
        } else {
            for (int i = 0; i < cookies.length; i++) {
                Cookie c = cookies[i];
                System.out.println("======cookie=======" + c.getName() + ":" + c.getValue());
            }
            System.out.println("======end=======");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
