package controller;

import service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController",urlPatterns = "/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username =request.getParameter("username");
        String password =request.getParameter("password");

        Boolean isvalidUser = new LoginService().getUserInfo(username,password);

        if(isvalidUser){
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            response.sendRedirect("/expense");
        }else {
            request.setAttribute("message", "Username ot Password incorrect");
            RequestDispatcher rd = request.getRequestDispatcher("/pages/login/login.jsp");
            rd.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/pages/login/login.jsp");
        rd.forward(request,response);
    }

}