package service;

import dbUtils.DBConnection;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    private String table_name = "users";
    Connection connection = null;

    public LoginService() {
        connection = new DBConnection().getConnection();
    }

    public Boolean getUserInfo(String username, String password) {

        try {

            String query = "SELECT * FROM " + table_name + " WHERE username=? and password =?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }
        return false;
    }
}