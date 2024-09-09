package com.example.material_save.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DBConfig {



  static   String HOST = "localhost";
    static  String PORT = "3306";
    static   String USER = "root";
    static String PASSWORD = "";
    static  String DATABASE = "material_db";
    static   String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

        // Méthode pour obtenir une connexion à la base de données
        public static Connection getConnection() {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la connexion à la base de données : " + e.getMessage(), e);
            }
        }
    }


