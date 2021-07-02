package com.traudat.app.service;


import com.traudat.app.entity.Category;
import com.traudat.app.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> search(Category category, String name);

    void save(Product product);
}
