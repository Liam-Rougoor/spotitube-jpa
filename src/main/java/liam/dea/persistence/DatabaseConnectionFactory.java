package liam.dea.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/spotitube?useSSL=false&serverTimezone=UTC", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
