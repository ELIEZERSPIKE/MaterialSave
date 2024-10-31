package com.example.material_save.Models;

import com.example.material_save.IDB.DBConfig;
import com.example.material_save.Interfaces.ManagerInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager implements ManagerInterface {

    private int id;
    private String username;
    private Connection connection;
    private String password;
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


//    @Override
//    public void register(Manager manager) throws SQLException {
//        connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            connection = DBConfig.getConnection();
//            String req = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
//            preparedStatement = connection.prepareStatement(req);
//
//            preparedStatement.setString(1, this.username);
//            preparedStatement.setString(2, this.email);
//            preparedStatement.setString(3, this.password);
//
//            preparedStatement.executeUpdate();
//
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    @Override
    public boolean isUsernameUnique(String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";

        try (Connection connection = DBConfig.getConnection()) {
            if (connection == null) {
                throw new SQLException("Failed to establish a database connection.");
            }

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1) == 0; // Returns false if the username exists
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error checking username uniqueness: " + e.getMessage(), e);
        }

        return true; // Returns true if the username is unique
    }

    @Override
    public boolean SignIn(Manager manager) throws SQLException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
        connection = DBConfig.getConnection();
        int rows = 0;
        try {
            if (connection != null) {
                String req = "SELECT * FROM users WHERE username = ? AND password = ? ";
                PreparedStatement preparedStatement = connection.prepareStatement(req);

                preparedStatement.setString(1, manager.getUsername());
                preparedStatement.setString(2, manager.getPassword());

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    rows++;
                }
                preparedStatement.close();
                this.connection.close();
            }
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void register(Manager manager) throws SQLException {
        if (isUsernameUnique(manager.getUsername())) {
            // Hash the password before storing it
            String hashedPassword = hashPassword(manager.getPassword());

            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            try (Connection connection = DBConfig.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, manager.getUsername());
                statement.setString(2, hashedPassword);
                statement.setString(3, manager.getEmail());

                statement.executeUpdate();
            } catch (SQLException e) {
                // Log the exception (optional)
                throw new SQLException("Error during registration: " + e.getMessage(), e);
            }
        } else {
            throw new SQLException("Le nom d'utilisateur est déjà pris.");
        }
    }

    private String hashPassword(String password) {
        // Implement your password hashing logic here
        return password; // Placeholder: Replace with actual hashing
    }
}






