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
    private  int id;
    private String name;
    private  String State;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Connection connection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public Category(int id, String name, String state, String description) {
        this.id = id;
        this.name = name;
        State = state;
        this.description = description;
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
