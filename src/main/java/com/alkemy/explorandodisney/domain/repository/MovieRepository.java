package com.alkemy.explorandodisney.domain.repository;

import com.alkemy.explorandodisney.domain.Movie;
import com.alkemy.explorandodisney.domain.MovieList;
import com.alkemy.explorandodisney.persistence.entity.Pelicula;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    void createMovie(String title, LocalDate creationDate, Integer score, Long category);
    List<MovieList> getAll();
    List<MovieList> getByTitle(String title);
    List<MovieList> getByCategory(String category);
    List<MovieList> getByFechaDesc();
    List<MovieList> getByFechaAsc();
    Optional<Movie> getByExcactTitle(String title);
    Optional<Movie> getDescriptionMovie(Long idPelicula);
    Movie save (Movie movie);
    void updateMovie(String title, LocalDate creationDate, Integer score, Long category, Long id);
    void updateImage(String image,Long idMovie);
    void deleteByIdMovie(Long idMovie);
    void deleteByTitle(String title);
    void deleteRelation (Long idPelicula);
}
