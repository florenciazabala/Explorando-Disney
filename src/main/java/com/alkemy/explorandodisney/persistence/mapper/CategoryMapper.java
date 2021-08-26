package com.alkemy.explorandodisney.persistence.mapper;

import com.alkemy.explorandodisney.domain.Category;
import com.alkemy.explorandodisney.persistence.entity.Categoria;
import com.alkemy.explorandodisney.persistence.entity.Pelicula;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses={MovieMapper.class})
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "idCategoria", target ="idCategory" ),
            @Mapping(source = "imagen", target = "picture"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "peliculas", target = "movies")
    })
    Category toCategory(Categoria categoria);
    List<Category> toCategorys(List<Categoria> categorias);

    /*@InheritInverseConfiguration
    @Mapping(target = "peliculas", ignore = true)
    Categoria toCategoria(Category category);
    List<Categoria> toCategorias(List<Category> categorys);*/

    default String fromMovie(Pelicula pelicula) {
        return pelicula == null ? null : pelicula.getTitulo();
    }


}
