package com.example.material_save.Models;

import com.example.material_save.IDB.DBConfig;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import java.util.ResourceBundle;

public class Maintenance implements Initializable {

    private int numeroMateriel;
    private String categorie;
    private String charge;



    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    private String probleme;
    private Date date ;
    private String statut;
    private Connection connection;



    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumeroMateriel() {
        return numeroMateriel;
    }

    public void setNumeroMateriel(int numeroMateriel) {
        this.numeroMateriel = numeroMateriel;
    }

    public Maintenance(int numeroMateriel, String categorie, String charge, String probleme, Date date, String statut) {
        this.numeroMateriel = numeroMateriel;
        this.categorie = categorie;
        this.charge = charge;
        this.probleme = probleme;
        this.date = date;
        this.statut = statut;
    }

//    public void register(Maintenance maintenance) throws IOException{
//
//        PreparedStatement preparedStatement = null;
//
//        try{
//            connection = DBConfig.getConnection();
//            String req = " INSERT INTO maintenances " +
//                     "(numeroMateriel, categorie, charge, date,probleme, statut)"
//                       + "VALUES (?,?,?,?,?,?) ";
//            preparedStatement = connection.prepareStatement(req);
//           preparedStatement.setInt(1, this.numeroMateriel);
//            preparedStatement.setString(2, this.categorie);
//            preparedStatement.setString(3,this.probleme);
//            preparedStatement.setString(4, this.charge);
//            preparedStatement.setDate(5, this.date);
//            preparedStatement.setString(6, this.statut);
//
//            preparedStatement.executeUpdate();
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
public void register(Maintenance maintenance) throws IOException {
    PreparedStatement preparedStatement = null;

    try {
        connection = DBConfig.getConnection();
        String req = "INSERT INTO maintenances " +
                "(numeroMateriel, categorie, charge, date, probleme, statut) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(req);

        preparedStatement.setInt(1, this.numeroMateriel); // numeroMateriel
        preparedStatement.setString(2, this.categorie); // categorie
        preparedStatement.setString(3, this.charge); // charge
        preparedStatement.setDate(4, this.date); // date
        preparedStatement.setString(5, this.probleme); // probleme
        preparedStatement.setString(6, this.statut); // statut

        preparedStatement.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
