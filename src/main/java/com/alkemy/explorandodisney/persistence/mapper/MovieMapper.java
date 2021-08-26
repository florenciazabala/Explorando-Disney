package com.alkemy.explorandodisney.persistence.mapper;

import com.alkemy.explorandodisney.domain.Movie;
import com.alkemy.explorandodisney.persistence.entity.Categoria;
import com.alkemy.explorandodisney.persistence.entity.Pelicula;
import com.alkemy.explorandodisney.persistence.entity.Personaje;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CharacterMapper.class,CategoryMapper.class})
public interface MovieMapper {
    @Mappings({
            @Mapping(source = "idPelicula",target = "idMovie"),
            @Mapping(source = "imagen",target = "picture"),
            @Mapping(source = "titulo",target = "title"),
            @Mapping(source = "fechaCreacion",target = "creationDate",dateFormat = "dd/MM/yyyy"),
            @Mapping(source = "calificacion",target = "score"),
            @Mapping(source = "categoria",target = "category"),
            @Mapping(source = "personajes",target = "characters")
    })
    Movie toMovie(Pelicula pelicula);
    List<Movie> toMovies(List<Pelicula> peliculas);

    @InheritInverseConfiguration
    @Mapping(target = "categoria",ignore = true)
    @Mapping(target = "personajes",ignore = true)
    Pelicula toPelicula(Movie movie);
    List<Pelicula> toPeliculas(List<Movie> movies);

    default String fromCharacter(Personaje personaje) {
        return personaje == null ? null : personaje.getNombre();
    }

    default String fromCategry(Categoria categoria) {
        return categoria == null ? null : categoria.getNombre();
    }

}
