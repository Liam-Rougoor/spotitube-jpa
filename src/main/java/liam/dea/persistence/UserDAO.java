package liam.dea.persistence;

import com.mysql.cj.protocol.Resultset;
import liam.dea.dataobjects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User getUserByName(String name) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
        ) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createUserDTO(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByNameAndPassword(String name, String password) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createUserDTO(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User createUserDTO(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setUser(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
        }
        return user;
    }
}
