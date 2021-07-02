package com.traudat.app.repo;

import com.traudat.app.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Integer> {
}
