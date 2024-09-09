package com.example.material_save.Interfaces;

import com.example.material_save.Models.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryInterface {
    void create(Category category) throws SQLException;

    void update(Category category) throws  SQLException;

    void delete(Category category) throws  SQLException;

    List<Category> list() throws SQLException;
}
