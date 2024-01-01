package example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 第三种实现方式，是基于HTTP的方式
 */
// @WebServlet(name = "hello3", urlPatterns = "/s3")
public class MyServlet3 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyServlet3() {
        super();
    }

    //处理get请求
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("hello,this is a HttpServlet");
    }

    //处理post请求
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
