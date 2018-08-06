package service;

import dbUtils.DBConnection;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private String table_name = "users";
    Connection connection = null;

    public UserService() {
        connection = new DBConnection().getConnection();
    }
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();

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

                User user = new User();
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));

                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection); //finally close the connection to db
        }

        return userList;
    }
    public boolean  saveUser(User user) {


        String query = "INSERT INTO " + table_name + " (username,password,email) VALUES (?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }
        System.out.println("query = " + query);
        return false;
    }
    public User getUserInfo(String id) {
        try {
            User userInfo = new User();
            String query = "SELECT * FROM " + table_name + " WHERE id=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                userInfo.setId(Integer.parseInt(rs.getString("id")));
                userInfo.setUsername("username");
                userInfo.setPassword("password");
                userInfo.setEmail("email");
            }
            return userInfo;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }

        return null;
    }

    public boolean updateRecord(User user) {
        try {
            String query = "UPDATE " + table_name + " SET username =?, password =?, email=? WHERE id=?;";
            PreparedStatement pst = connection.prepareStatement(query);
            pst = connection.prepareStatement(query);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            pst.setInt(4, user.getId());
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
    public boolean deleteUser(String id) {
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