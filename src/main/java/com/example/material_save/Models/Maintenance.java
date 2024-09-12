package com.example.material_save.Models;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Maintenance implements Initializable {

    private int id;

    private String category;

    private Date date ;

    private String problem;

    private  String charge ;

    private  String locate;

    public Maintenance(int id, String category, Date date, String problem, String charge, String locate) {
        this.id = id;
        this.category = category;
        this.date = date;
        this.problem = problem;
        this.charge = charge;
        this.locate = locate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
