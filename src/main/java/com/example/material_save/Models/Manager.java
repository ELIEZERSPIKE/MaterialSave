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


    @Override
    public void register(Manager manager) throws SQLException {
        connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConfig.getConnection();
            String req = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(req);

            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);

            preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
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

}
