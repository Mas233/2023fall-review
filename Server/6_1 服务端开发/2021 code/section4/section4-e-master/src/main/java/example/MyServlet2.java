package example;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class MyServlet2 extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("service2...");
        PrintWriter pw = response.getWriter();
        pw.println("hello,this is a GenericServlet");
    }
}
