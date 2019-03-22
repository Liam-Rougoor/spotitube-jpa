package liam.dea.persistence;

import liam.dea.exceptions.DatabaseItemNotFoundException;
import liam.dea.exceptions.InvalidCredentialsException;
import liam.dea.dataobjects.User;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class DefaultUserDAO implements UserDAO {

    @Override
    public User getUserByName(String name) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
        ) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createUserDTO(resultSet);
            }
            throw new DatabaseItemNotFoundException("User " + name + " not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createUserDTO(resultSet);
            }
            throw new InvalidCredentialsException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User createUserDTO(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUser(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        return user;
    }
}

