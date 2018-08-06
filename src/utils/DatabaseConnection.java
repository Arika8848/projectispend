package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class
DatabaseConnection {

        String url = "jdbc:mysql://localhost:3306/java_practical";
        String userName = "root";
        String password = "";
        Connection connection = null;

        public DatabaseConnection() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url,userName, password);
                System.out.println("Database Connected");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        public PreparedStatement getPreparedStatement(String query){
            PreparedStatement pstm = null;
            try{
                pstm = connection.prepareStatement(query);
            }catch (SQLException e){
                e.printStackTrace();;
            }
            return pstm;
        }



    }