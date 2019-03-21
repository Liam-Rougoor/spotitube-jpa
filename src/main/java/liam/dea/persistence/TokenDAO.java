package liam.dea.persistence;

import liam.dea.exceptions.InvalidTokenException;
import liam.dea.dataobjects.Login;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

public class TokenDAO {

    public String getUserWithToken(String token) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM token WHERE token = ?");
        ) {
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("user");
            }
            throw new InvalidTokenException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTokenOfUser(String username){
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM token WHERE user = ?");
        ) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            String token = "";
            if (resultSet.next()) {
                token = resultSet.getString("token");
            }
            return token;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNewTokenForUser(String username){
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO token VALUES(?, ?, ?)");
        ) {
            preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, username);
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now())); //TODO set proper date
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean tokenIsValid(String token){
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM token WHERE token = ?");
        ) {
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Login getLogin(String user){
        return new Login(new UserDAO().getUserByName(user).getName(), getTokenOfUser(user));
    }
}
