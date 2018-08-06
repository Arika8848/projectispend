package controller;

import domain.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController" ,urlPatterns = "/user")
public class UserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action") == null ? "list" : request.getParameter("action");
        System.out.println("action = " + action);


        if (action.equalsIgnoreCase("list")) {
            List<User> userList = new UserService().getUserList();

            request.setAttribute("userlist", userList);
            RequestDispatcher rd = request.getRequestDispatcher("/pages/user/user.jsp");
            rd.forward(request, response);
        } else if (action.equalsIgnoreCase("add")) {
            response.sendRedirect("/pages/user/addUser.jsp");
            return; //to stop further code execution (optional)
        }

        else if (action.equalsIgnoreCase("addNew")) {
            String username = request.getParameter("username");
            System.out.println("username = " + username);
            String password = request.getParameter("password");
            System.out.println("password = " + password);
            String email = request.getParameter("email");
            System.out.println("email = " + email);
            System.out.println("request = [" + request + "], response = [" + response + "]");
            Boolean isSaved = new UserService().saveUser(new User(username, password, email));

            if (isSaved) {
                System.out.println("saved");
                //message to user with either request or session or any othe method
                response.sendRedirect("/pages/expensejsp");
            }
        }

        else if (action.equalsIgnoreCase("update")) {
            String idToUpdate = request.getParameter("id");

            User userToUpdate = new UserService().getUserInfo(idToUpdate);
            request.setAttribute("userInfo", userToUpdate);
            request.getRequestDispatcher("/pages/user/addUser.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("updateUser")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            Boolean isUpdated = new UserService().updateRecord(new User(id, username, password, email));

            if (isUpdated) {
                System.out.println("updated");
                //message to user with either request or session or any other method
                response.sendRedirect("/user");
            }
        } else if (action.equalsIgnoreCase("delete")) {
            String id = request.getParameter("id"); // will be passed via url from href
            Boolean isDeleted = new UserService().deleteUser(id);
            System.out.println("isDeleted = " + isDeleted);
            if (isDeleted) {
                System.out.println("deleted");
                //message to user with either request or session or any other method
                response.sendRedirect("/user");
            }
        }
    }
}