package com.traudat.app.repo;

import com.traudat.app.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Integer> {

}
