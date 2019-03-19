package liam.dea;

import liam.dea.persistence.UserDAO;

import java.sql.*;

public class DatabaseMain {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        System.out.println(userDAO.getUserByName("liam1").getUser());
    }
}
