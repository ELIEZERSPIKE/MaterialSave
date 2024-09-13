package com.example.material_save.Models;

import com.example.material_save.IDB.DBConfig;
import com.example.material_save.Interfaces.MaterielInterface;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDateTime;

public class Material implements MaterielInterface {

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
    private String problem;
    private String charge;

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

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }



//  public Material(int materialNumber, String name, String etat, String marque, String locale, String category, Date date, String utilisateur, String statut, String problem,
//                    String charge) {
//        this.materialNumber = materialNumber;
//       this.name = name;
//      this.etat = etat;
//       this.marque = marque;
//        this.locale = locale;
//       this.category = category;
//       this.date = date;
//       this.utilisateur = utilisateur;
//       this.statut = statut;
//      this.charge = (charge != null) ? charge : "";
// }


//    public Material(int materialNumber, int id, String name,
//                    String etat, String marque,
//                    String locale, String category, Date date, String utilisateur,
//                    String statut, String problem, String charge)
//    {
//       this.materialNumber = materialNumber;
//       this.id = id;
//        this.name = name;
//       this.etat = etat;
//        this.marque = marque;
//        this.locale = locale;
//        this.category = category;
//        this.date = date;
//        this.utilisateur = utilisateur;
//        this.statut = statut;
//        this.problem = problem;
//        this.charge = charge;
//    }


    public Material(int materialNumber, String name, String etat, String marque, String locale, String category, Date date, String utilisateur, String statut, String problem, String charge) {
        this.materialNumber = materialNumber;
        this.name = name;
        this.etat = etat;
        this.marque = marque;
        this.locale = locale;
        this.category = category;
        this.date = date;
        this.utilisateur = utilisateur;
        this.statut = statut;
        this.problem = (problem != null) ? problem : "";
        this.charge = (charge != null) ? charge : "";
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

//            java.util.Date date = new java.util.Date();
//            java.sql.Date sqlDate = new  java.sql.Date(date.getTime());
//            preparedStatement.setString(10, String.valueOf(sqlDate));

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



//    public boolean updateMaterial(Material material) throws SQLException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            connection = DBConfig.getConnection();
//            String query = "UPDATE material SET " +
//                    "nom = ?, marque = ?, etat = ?, local = ?, " +
//                    "categorie = ?, date = ?, utilisateur = ?, statut = ? " +
//                    "WHERE numeroMateriel = ?";
//
//            preparedStatement = connection.prepareStatement(query);
//
//            preparedStatement.setString(1, material.getName());
//            preparedStatement.setString(2, material.getMarque());
//            preparedStatement.setString(3, material.getEtat());
//            preparedStatement.setString(4, material.getLocale());
//            preparedStatement.setString(5, material.getCategory());
//            preparedStatement.setDate(6, material.getDate());
//            preparedStatement.setString(7, material.getUtilisateur());
//            preparedStatement.setString(8, material.getStatut());
//            preparedStatement.setInt(9, material.getMaterialNumber());
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            return rowsAffected > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        } finally {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }

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


}








