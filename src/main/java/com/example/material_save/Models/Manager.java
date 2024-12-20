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
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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
    public boolean isUsernameUnique(String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";

        try (Connection connection = DBConfig.getConnection()) {
            if (connection == null) {
                throw new SQLException("Erreur de connection a la base de donnée.");
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
        connection = DBConfig.getConnection();
        try {
            if (connection != null) {
                String req = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(req);

                preparedStatement.setString(1, manager.getUsername());
               preparedStatement.setString(2, manager.getPassword()); // Utiliser le mot de passe haché
//                preparedStatement.setString(2, this.getPassword());

                ResultSet resultSet = preparedStatement.executeQuery();

                boolean userExists = resultSet.next(); // Renvoie vrai si un utilisateur a été trouvé
                preparedStatement.close();
                return userExists;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close(); // Toujours fermer la connexion
            }
        }
        return false;
    }

    @Override
    public void register(Manager manager) throws SQLException {
        if (isUsernameUnique(manager.getUsername())) {
            // Vérification du nombre d'administrateurs
            if (manager.getRole().equals("admin")) {
                if (countAdmins() >= 2) {
                    throw new SQLException("Impossible de créer plus de 2 administrateurs.");
                }
            }

            // Hash the password before storing it
            String hashedPassword = hashPassword(manager.getPassword());

            String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
            try (Connection connection = DBConfig.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, manager.getUsername());
                statement.setString(2, hashedPassword);
                statement.setString(3, manager.getEmail());
                statement.setString(4, manager.getRole());

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

        return password;
    }
    public int getUserIdByUsername(String username) throws SQLException {
        int userId = -1; // Valeur par défaut en cas d'erreur
        String query = "SELECT userId FROM users WHERE username = ?";

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt("userId");
            }
        }

        return userId;
    }
    public int countAdmins() throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE role = 'admin'";
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }
    public String getUserRoleByUsername(String username) throws SQLException {
        // Établir la connexion et exécuter la requête pour récupérer le rôle
        String role = null;
        String query = "SELECT role FROM users WHERE username = ?";
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("role");
            }
        }
        return role;
    }


}






