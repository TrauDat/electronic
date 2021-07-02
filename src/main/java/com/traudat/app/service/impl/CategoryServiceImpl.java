package com.traudat.app.service.impl;

import com.traudat.app.entity.Category;
import com.traudat.app.model.ElicException;
import com.traudat.app.repo.CategoryRepository;
import com.traudat.app.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {

        if (StringUtils.isEmpty(category.getName())) {
            throw new ElicException("Please enter category name.");
        }

        categoryRepository.save(category);
    }
}
