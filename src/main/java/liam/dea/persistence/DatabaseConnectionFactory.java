package liam.dea.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionFactory {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/spotitube?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "spotitube";
    private static final String DB_PASSWORD = "spotitube";

    private static Properties properties = loadProperties();

    static {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        String propertiesPath = DatabaseConnectionFactory.class.getClassLoader().getResource("").getPath() + "database.properties";
        try
                (
                        FileInputStream fin = new FileInputStream(propertiesPath)
                ) {
            properties.load(fin);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            return generateDefaultProperties();
        }
    }

    private static Properties generateDefaultProperties() {
        Properties properties = new Properties();
        properties.setProperty("db.driver", DB_DRIVER);
        properties.setProperty("db.username", DB_USER);
        properties.setProperty("db.password", DB_PASSWORD);
        properties.setProperty("db.url", DB_URL);
        return properties;
    }

    public Connection createConnection() {
        try {
            return DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
