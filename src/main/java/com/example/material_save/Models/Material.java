package com.example.material_save.Models;

import com.example.material_save.IDB.DBConfig;
import com.example.material_save.Interfaces.MaterielInterface;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDateTime;

public class Material  {

    private int materialNumber;

    private int id;

    public Material() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    private String etat;

    private String marque;

    private String locale;
    private String category;
    private Date date ;
    private String utilisateur;
    private String statut;


    private Connection connection;

    private ResultSet resultSet;
    private  Alert alert;

//    public Material() {
//
//    }


    public int getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(int materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }



    public Material(int materialNumber, String name, String etat, String marque, String locale, String category, Date date, String utilisateur, String statut) {
        this.materialNumber = materialNumber;
        this.name = name;
        this.etat = etat;
        this.marque = marque;
        this.locale = locale;
        this.category = category;
        this.date = date;
        this.utilisateur = utilisateur;
        this.statut = statut;

    }

    public void register(Material material) throws SQLException {
        connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConfig.getConnection();
            String req = "INSERT INTO  material "
                    + "(numeroMateriel, nom, marque, etat,local,categorie,date,utilisateur,statut)"
                    + "VALUES (?,?,?,?,?,?,?,?,?) ";
            preparedStatement = connection.prepareStatement(req);


            preparedStatement.setInt(1, this.materialNumber);
            preparedStatement.setString(2, this.name);
            preparedStatement.setString(3,this.marque);
            preparedStatement.setString(4,this.etat);
            preparedStatement.setString(5, this.locale);
            preparedStatement.setString(6, this.category);

            preparedStatement.setDate(7, this.date);
            preparedStatement.setString(8, this.utilisateur);
            preparedStatement.setString(9,  this.statut);
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
    public boolean updateMaterial(Material material) throws SQLException {

        connection = null;
        PreparedStatement preparedStatement  = null;

        String query = "UPDATE material SET numeroMateriel = ?," +
                " nom = ?, marque = ?, etat = ?, local = ?, categorie = ?," +
                " date = ?, utilisateur = ?, statut = ? WHERE numeroMateriel = ?";
        try {
            connection = DBConfig.getConnection();
            preparedStatement  = connection.prepareStatement(query);



            preparedStatement.setInt(1, material.getMaterialNumber());
            preparedStatement.setString(2, material.getName());
            preparedStatement.setString(3, material.getMarque());
            preparedStatement.setString(4, material.getEtat());
            preparedStatement.setString(5, material.getLocale());
            preparedStatement.setString(6, material.getCategory());
            preparedStatement.setDate(7, material.getDate());
            preparedStatement.setString(8, material.getUtilisateur());
            preparedStatement.setString(9, material.getStatut());
            preparedStatement.setInt(10, material.getMaterialNumber());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        }
    }

    public boolean DeleteMaterial(Material material) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String query = "DELETE FROM `material` WHERE `numeroMateriel` = ?";

        try {

            connection = DBConfig.getConnection();

            // Préparez la requête
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, material.getMaterialNumber());


            int rowsDeleted = preparedStatement.executeUpdate();

            // Retournez true si des lignes ont été supprimées, sinon false
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Laissez passer l'exception pour une gestion ultérieure
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


}








