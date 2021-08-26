package com.alkemy.explorandodisney.domain.repository;

import com.alkemy.explorandodisney.domain.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAll();
    Category getById(Long id);
    Category getByName(String name);
}
