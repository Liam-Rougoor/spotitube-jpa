package liam.dea.persistence;

import liam.dea.dataobjects.Login;
import liam.dea.exceptions.InvalidTokenException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

@Default
public class DefaultTokenDAO implements TokenDAO {

    private UserDAO userDAO;

    public DefaultTokenDAO() {
    }

    @Inject
    public DefaultTokenDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String getUserWithToken(String token) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM token WHERE token = ?")
        ) {
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("user");
                return username;
            }
            throw new InvalidTokenException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String createNewTokenForUser(String username) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO token VALUES(?, ?, ?)")
        ) {
            String token = UUID.randomUUID().toString();
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, username);
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(2);

            preparedStatement.setDate(3, Date.valueOf(tomorrow));
            preparedStatement.execute();
            return token;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tokenIsValid(String token) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM token WHERE token = ? AND CURDATE() < expire_date")
        ) {
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Login getLogin(String user) {
        return new Login(userDAO.getUserByUsername(user).getName(), createNewTokenForUser(user));
    }
}
