package com.traudat.app.service.impl;

import org.apache.commons.lang3.StringUtils;
import com.traudat.app.entity.Category;
import com.traudat.app.entity.Product;
import com.traudat.app.model.ElicException;
import com.traudat.app.repo.ProductRepository;
import com.traudat.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> search(Category category, String name) {
        StringBuffer sb = new StringBuffer("select p from Product p where 1 = 1");
        Map<String, Object> params = new HashMap<>();
        if (null != category) {
            sb.append(" and p.category = :category");
            params.put("category", category);
        }
        if (!StringUtils.isEmpty(name)) {
            sb.append(" and lower(p.name) like lower(:name)");
            params.put("name", name.concat("%s"));
        }
        return productRepository.findByQuery(sb.toString(), params);
    }


    @Override
    public void save(Product product) {

        if (null == product.getCategory()) {
            throw new ElicException("Please select Category");
        }

        if (StringUtils.isEmpty(product.getName())) {
            throw new ElicException("Please enter Product name");
        }

        if (product.getPrice() == 0) {
            throw new ElicException("Please enter Product price");
        }

        productRepository.save(product);
    }
}
