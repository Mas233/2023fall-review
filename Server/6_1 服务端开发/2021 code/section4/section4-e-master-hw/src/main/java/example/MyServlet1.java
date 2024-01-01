package example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * servlet 要遵守规范开发，规范的标准是3.0，前后有比较大的差别
 */
// @WebServlet(name = "hello1", urlPatterns = "/s1")
public class MyServlet1 implements Servlet {

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 初始化方法
     *
     * @param arg0
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig arg0) throws ServletException {
        System.out.println("init it in servlet1");
    }

    /**
     * 处理来自客户端的请求和返回给客户端的结果
     * 访问/section4-e/s1
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("service1...");

        //打印在浏览器上
        PrintWriter pw = response.getWriter();
        pw.println("hello,this is servlet1");
    }

}
