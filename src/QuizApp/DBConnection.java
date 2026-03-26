package QuizApp;

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/quizdb";
            String user = "root";
            String pass = "Jashveer@2004"; // change if needed

            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}