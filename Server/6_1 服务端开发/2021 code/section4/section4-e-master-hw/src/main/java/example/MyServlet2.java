package example;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 第二种实现方式 父类已经帮助完成了部分实现
 *
 */
// @WebServlet(name = "hello2", urlPatterns = "/s2")
public class MyServlet2 extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("service2...");
        PrintWriter pw = response.getWriter();
        pw.println("hello,this is a GenericServlet");
    }
}
