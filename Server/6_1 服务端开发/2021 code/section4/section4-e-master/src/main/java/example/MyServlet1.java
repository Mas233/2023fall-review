package example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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

    @Override
    public void init(ServletConfig arg0) throws ServletException {
        System.out.println("init it in servlet1");
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("service1...");

        //打印在浏览器上
        PrintWriter pw = response.getWriter();
        pw.println("hello,this is servlet1");
    }

}
