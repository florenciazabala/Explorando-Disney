package com.alkemy.explorandodisney.persistence.crud;

import com.alkemy.explorandodisney.persistence.entity.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaCrudRepository extends CrudRepository<Categoria,Long> {
    List<Categoria> findAll();
    Categoria findByIdCategoria(Long id);
    @Query("select c from Categoria c where c.nombre = :nombre")
    Categoria findByNombre(@Param("nombre") String nombre);
}
