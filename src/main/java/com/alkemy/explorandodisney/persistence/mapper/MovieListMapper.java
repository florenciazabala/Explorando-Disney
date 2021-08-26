package com.alkemy.explorandodisney.persistence.mapper;

import com.alkemy.explorandodisney.domain.Movie;
import com.alkemy.explorandodisney.domain.MovieList;
import com.alkemy.explorandodisney.persistence.entity.Pelicula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieListMapper {
    @Mappings({
            @Mapping(source = "imagen",target = "picture"),
            @Mapping(source = "titulo",target = "title"),
            @Mapping(source = "fechaCreacion",target = "creationDate",dateFormat = "dd/MM/yyyy"),
    })
    MovieList toMovie(Pelicula pelicula);
    List<MovieList> toMovies(List<Pelicula> peliculas);


}
