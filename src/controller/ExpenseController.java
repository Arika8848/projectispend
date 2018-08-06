package controller;

import domain.Expense;
import service.ExpenseService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Arika on 4/13/2018.
 */
@WebServlet(name = "ExpenseController" ,urlPatterns = "/expense")
public class ExpenseController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //if no action parameter is passed, then default will be "list"
        String action = request.getParameter("action") == null ? "list" : request.getParameter("action");
        System.out.println("action = " + action);

        // display book list page
        if (action.equalsIgnoreCase("list")) {
            List<Expense> expenseList = new ExpenseService().getExpenseList();
            //query execution end
            request.setAttribute("expenseList", expenseList); // add the list obtained from DB to request to sent to jsp page
            RequestDispatcher rd = request.getRequestDispatcher("/pages/expense.jsp"); // this holds table to show the list of books
            rd.forward(request, response);
        }
        // to display add Book form page
        else if (action.equalsIgnoreCase("add")) {
            response.sendRedirect("/pages/addExpense.jsp");
        }
        // to add to db
        else if (action.equalsIgnoreCase("addNew")) { // for adding new book to db with values which we get from form(/pages/addBook.jsp)
            String name = request.getParameter("name");
            System.out.println(name);

            String price = request.getParameter("price");
            System.out.println(price);
            String expense_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); //get today's date in yyyy-MM-dd format
//creating new Book object via constructor and pass to BookService
            Boolean isSaved = new ExpenseService().saveExpense(new Expense(name, price, expense_date));

            if (isSaved) {
                System.out.println("saved");
                //message to user with either request or session or any othe method
                response.sendRedirect("/expense"); // "/book" will again redirect to BookController as defined in web.xml with no action
            }
        }
        //updating particular book
        else if (action.equalsIgnoreCase("update")) {
            String idToUpdate = request.getParameter("id"); //get id to update (here id will be passed via url (link creation part))

            Expense expenseToUpdate = new ExpenseService().getExpenseInfo(idToUpdate);
            request.setAttribute("expenseInfo", expenseToUpdate); // we will check for bookInfo in jsp page
            request.getRequestDispatcher("/pages/addExpense.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("updateExpense")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String expense_date = request.getParameter("expense_date");
            Boolean isUpdated = new ExpenseService().updateRecord(new Expense(id, name, price, expense_date));

            if (isUpdated) {
                System.out.println("updated");
                //message to user with either request or session or any other method
                response.sendRedirect("/expense"); // "/book" will again redirect to BookController as defined in web.xml with no action
            }
        } else if (action.equalsIgnoreCase("delete")) {
            String id = request.getParameter("id"); // will be passed via url from href
            Boolean isDeleted = new ExpenseService().deleteExpense(id);
            System.out.println("isDeleted = " + isDeleted);
            if (isDeleted) {
                System.out.println("deleted");
                //message to user with either request or session or any other method
                response.sendRedirect("/expense"); // "/book" will again redirect to BookController as defined in web.xml with no action
            }
        }
    }
}
