package com.example.material_save.Models;

import com.example.material_save.Interfaces.CategoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.material_save.IDB.DBConfig;

public class Category {

    private String CategoryName;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String CategoryDescription;

    private String CategoryState;

    private Connection connection;

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryDescription() {
        return CategoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        CategoryDescription = categoryDescription;
    }

    public String getCategoryState() {
        return CategoryState;
    }

    public void setCategoryState(String categoryState) {
        CategoryState = categoryState;
    }

    public Category(String categoryName, String categoryDescription, String categoryState) {
        CategoryName = categoryName;
        CategoryDescription = categoryDescription;
        CategoryState = categoryState;
    }

    public void register(Category category) throws SQLException {
        connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConfig.getConnection();
            String req = "INSERT INTO  category "
                    + "(NomCategory, Description, Etat)"
                    + "VALUES (?,?,?) ";
            preparedStatement = connection.prepareStatement(req);

            preparedStatement.setString(1, this.CategoryName);
            preparedStatement.setString(2, this.CategoryDescription);
            preparedStatement.setString(3,this.CategoryState);


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

    public boolean updateCategory(Category category) throws SQLException {

        connection = null;
        PreparedStatement preparedStatement  = null;

        String query = "UPDATE category SET NomCategory = ?," +
                " Description = ?, Etat = ? WHERE NomCategory = ? ";
        try {
            connection = DBConfig.getConnection();
            preparedStatement  = connection.prepareStatement(query);



            preparedStatement.setString(1, category.getCategoryDescription());
            preparedStatement.setString(2,category.getCategoryName());
            preparedStatement.setString(3,getCategoryState());
            preparedStatement.setString(4, category.getCategoryName());


            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        }
    }

    public boolean DeleteCategory(Category category) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String query = "DELETE FROM `category` WHERE `NomCategory` = ?";

        try {

            connection = DBConfig.getConnection();

            // Préparez la requête
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, category.getCategoryName());


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










































    //    @Override
//    public  void create(Category category) throws SQLException{
//        connection = DBConfig.getConnection();
//        if(connection != null){
//            String req = "INSERT INTO categories(name, state) VALUES (?, ?)";
//            PreparedStatement preparedStatement = this.connection.prepareStatement(req);
//            preparedStatement.setString(1, category.getName());
//            preparedStatement.setString(2, category.getState());
//            int row = preparedStatement.executeUpdate();
//            System.out.println(String.valueOf(row));
//            preparedStatement.close();
//            this.connection.close();
//
//        }
//
//    }
//    @Override
//    public  void update(Category category) throws  SQLException{
//        connection = DBConfig.getConnection();
//        if(connection != null){
//            String req = "UPDATE categories SET name ? ,  state ? , WHERE id = ?";
//            PreparedStatement preparedStatement = this.connection.prepareStatement(req);
//            preparedStatement.setString(1, category.getName());
//            preparedStatement.setString(2, category.getState());
//            preparedStatement.setInt(3, category.getId());
//
//            int row = preparedStatement.executeUpdate();
//            preparedStatement.close();
//            this.connection.close();
//
//        }
//    }
//    @Override
//    public  void delete(Category category) throws  SQLException{
//        connection = DBConfig.getConnection();
//        if (connection != null){
//            String req =  "DELETE FROM categories  WHERE id = ?";
//            PreparedStatement preparedStatement = this.connection.prepareStatement(req);
//            preparedStatement.setInt(1, id);
//            int row = preparedStatement.executeUpdate();
//            System.out.println(String.valueOf(row));
//            preparedStatement.close();
//            this.connection.close();
//        }
//    }
//
//    @Override
//    public List<Category> list() throws SQLException{
//        List<Category> categories = new ArrayList<>();
//
//        connection = DBConfig.getConnection();
//        if(connection != null){
//            String req = "SELECT * FROM categories";
//            PreparedStatement preparedStatement = this.connection.prepareStatement(req);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                Category category = new Category();
//                category.setId(resultSet.getInt(id));
//                category.setName(resultSet.getString("name"));
//                category.setName(resultSet.getString("state"));
//                categories.add(category);
//            }
//            preparedStatement.close();
//        this.connection.close();
//        }
//        return  categories;
//    }

}
