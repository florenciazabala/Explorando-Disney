package com.alkemy.explorandodisney.persistence;

import com.alkemy.explorandodisney.domain.Category;
import com.alkemy.explorandodisney.domain.repository.CategoryRepository;
import com.alkemy.explorandodisney.persistence.crud.CategoriaCrudRepository;
import com.alkemy.explorandodisney.persistence.entity.Categoria;
import com.alkemy.explorandodisney.persistence.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaRepository implements CategoryRepository {
    @Autowired
    private CategoriaCrudRepository categoriaCrudRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAll() {
        List<Categoria> categorias = categoriaCrudRepository.findAll();
        return categoryMapper.toCategorys(categorias);
    }

    @Override
    public Category getById(Long id) {
        Categoria categoria = categoriaCrudRepository.findByIdCategoria(id);
        return categoryMapper.toCategory(categoria);
    }

    @Override
    public Category getByName(String name) {
        Categoria categoria = categoriaCrudRepository.findByNombre(name);
        return categoryMapper.toCategory(categoria);
    }
}
