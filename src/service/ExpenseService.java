package service;

import dbUtils.DBConnection;
import domain.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService {
    private String table_name = "expense";
    private Connection connection = null;

    public ExpenseService() {
        connection = new DBConnection().getConnection();
    }

    public List<Expense> getExpenseList() {
        List<Expense> expenseList = new ArrayList<>();

        String query = "SELECT * FROM " + table_name; //query
        PreparedStatement statement = null;
        //create preparedStatement
        try {
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //run the preparedstatement query
        try {
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                Expense expense = new Expense();
                expense.setId(rs.getString("id"));
                expense.setName(rs.getString("name"));
                expense.setPrice(rs.getString("price"));
                expense.setExpense_date(rs.getString("expense_date"));

                expenseList.add(expense);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection); //finally close the connection to db
        }

        return expenseList;
    }

    /**
     * Save to database
     */
    public boolean saveExpense(Expense expense) {
        String query = "INSERT INTO " + table_name + " (name , price, expense_date,user_id) VALUES (?,?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, expense.getName());
            pst.setString(2, expense.getPrice());
            pst.setString(3, expense.getExpense_date());
            return pst.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }
        System.out.println("query = " + query);
        return false;
    }

    /**
     * Get Info to Update particular book
     */

    public Expense getExpenseInfo(String id) {
        try {
            Expense expenseInfo = new Expense();
            String query = "SELECT * FROM " + table_name + " WHERE user_id=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                expenseInfo.setId(rs.getString("id"));
                expenseInfo.setName(rs.getString("name"));
                expenseInfo.setPrice(rs.getString("price"));
                expenseInfo.setExpense_date(rs.getString("expense_date"));
            }
            return expenseInfo;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }

        return null;
    }

    /**
     * Updating the existing record in db
     */
    public boolean updateRecord(Expense expense) {
        try {
            String query = "UPDATE " + table_name + " SET name =?,price=?, expense_date =? WHERE id=?;";
            PreparedStatement pst = connection.prepareStatement(query);
            pst = connection.prepareStatement(query);
            pst.setString(1, expense.getName());
            pst.setInt(2, Integer.parseInt(expense.getPrice()));
            pst.setString(3, expense.getExpense_date());
            pst.setString(4, expense.getId());
            return pst.executeUpdate() != 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }

        return false;
    }

    /**
     * Delete record from db
     *
     * @param id
     * @return
     */
    public boolean deleteExpense(String id) {
        try {
            String query = "DELETE FROM " + table_name + " WHERE id =?;";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, id);
            return pst.executeUpdate() != 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }

        return false;
    }
}
