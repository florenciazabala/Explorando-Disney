package com.alkemy.explorandodisney.persistence;

import com.alkemy.explorandodisney.domain.Movie;
import com.alkemy.explorandodisney.domain.MovieList;
import com.alkemy.explorandodisney.domain.repository.MovieRepository;
import com.alkemy.explorandodisney.persistence.crud.PeliculaCrudRepository;
import com.alkemy.explorandodisney.persistence.entity.Pelicula;
import com.alkemy.explorandodisney.persistence.mapper.MovieListMapper;
import com.alkemy.explorandodisney.persistence.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class PeliculaRepository implements MovieRepository {

    @Autowired
    private PeliculaCrudRepository peliculaCrudRepository;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieListMapper movieListMapper;

    @Override
    public void createMovie(String title, LocalDate creationDate, Integer score, Long category) {
        peliculaCrudRepository.save(title,creationDate,score,category);
    }

    @Override
    public List<MovieList> getAll() {
        List<Pelicula> peliculas = peliculaCrudRepository.findAll();
        return movieListMapper.toMovies(peliculas);
    }

    @Override
    public List<MovieList> getByTitle(String title) {
        List<Pelicula> peliculas = peliculaCrudRepository.findByTituloContaining(title);
        return movieListMapper.toMovies(peliculas);
    }

    @Override
    public List<MovieList> getByCategory(String category) {
        List<Pelicula> peliculas = peliculaCrudRepository.findByCategory(category);
        return movieListMapper.toMovies(peliculas);
    }

    @Override
    public List<MovieList> getByFechaDesc() {
        List<Pelicula> peliculas = peliculaCrudRepository.findOrderByFechaCreacionDesc();
        return movieListMapper.toMovies(peliculas);
    }

    @Override
    public List<MovieList> getByFechaAsc() {
        List<Pelicula> peliculas = peliculaCrudRepository.findOrderByFechaCreacionAsc();
        return movieListMapper.toMovies(peliculas);
    }

    @Override
    public Optional<Movie> getByExcactTitle(String title) {
        return peliculaCrudRepository.findByTitulo(title).map(pelicula ->movieMapper.toMovie(pelicula));
    }

    @Override
    public Optional<Movie> getDescriptionMovie(Long idPelicula) {
        return peliculaCrudRepository.findByIdPelicula(idPelicula).map(pelicula -> movieMapper.toMovie(pelicula));
    }


    //Create
    @Override
    public Movie save(Movie movie) {
        Pelicula pelicula = movieMapper.toPelicula(movie);
        return movieMapper.toMovie(peliculaCrudRepository.save(pelicula));
    }

    //Update
    @Override
    public void updateMovie(String title, LocalDate creationDate, Integer score, Long category, Long id) {
        peliculaCrudRepository.updateMovie(title,creationDate,score,category,id);
    }

    @Override
    public void updateImage(String image,Long idMovie){
        peliculaCrudRepository.updateImage(image,idMovie);
    }
    //Delete
    @Override
    public void deleteByIdMovie(Long idMovie) {
        peliculaCrudRepository.deleteByIdPelicula(idMovie);
    }

    @Override
    public void deleteByTitle(String title) {
        peliculaCrudRepository.deleteByTitulo(title);
    }

    @Override
    public void deleteRelation(Long idPelicula) {
        peliculaCrudRepository.deleteRelation(idPelicula);
    }

}
