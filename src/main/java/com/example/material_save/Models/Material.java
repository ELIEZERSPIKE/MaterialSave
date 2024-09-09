package com.example.material_save.Models;

import java.sql.Date;

public class Material {

    private int materialNumber;

    private String name;

    private String state;

    private String marque;

    private String locate;
    private String category;
    private Date date ;
    private String user;
    private String statut;
    private String problem;
    private String charge;


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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatut() {
        return statut;
    }
//jniikhuh
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

    public Material(int materialNumber, String name, String state, String marque, String locate, String category, Date date, String user,
                    String statut, String problem, String charge) {
        this.materialNumber = materialNumber;
        this.name = name;
        this.state = state;
        this.marque = marque;
        this.locate = locate;
        this.category = category;
        this.date = date;
        this.user = user;
        this.statut = statut;
        this.problem = problem;
        this.charge = charge;
    }


}
