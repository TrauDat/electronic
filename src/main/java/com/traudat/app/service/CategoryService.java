package com.traudat.app.service;

import com.traudat.app.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAll();

    void save(Category category);
}
