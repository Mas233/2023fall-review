package example;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("my filter init....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println(" do filter before enter servlet......");
        chain.doFilter(request, response);
        //这个地方就是调用了请求之后获得结果，然后向前端返回前
        //可以在这个地方打日志，不用AOP织入
        System.out.println(" do filter after finishing servlet......");
    }

    @Override
    public void destroy() {
        System.out.println("my filter destroy....");
    }

}