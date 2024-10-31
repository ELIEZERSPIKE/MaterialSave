package com.example.material_save.Interfaces;

import com.example.material_save.Models.Manager;

import java.sql.SQLException;

public interface ManagerInterface {

    void register(Manager manager) throws SQLException;

    boolean SignIn(Manager manager) throws SQLException;

    boolean isUsernameUnique(String username) throws SQLException;
}
