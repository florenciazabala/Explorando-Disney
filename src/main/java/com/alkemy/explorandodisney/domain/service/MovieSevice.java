package com.alkemy.explorandodisney.domain.service;

import com.alkemy.explorandodisney.domain.Category;
import com.alkemy.explorandodisney.domain.Character;
import com.alkemy.explorandodisney.domain.Movie;
import com.alkemy.explorandodisney.domain.MovieList;
import com.alkemy.explorandodisney.domain.repository.CategoryRepository;
import com.alkemy.explorandodisney.domain.repository.CharacterRepository;
import com.alkemy.explorandodisney.domain.repository.MovieRepository;
import com.alkemy.explorandodisney.persistence.entity.enums.MovieGenre;
import com.alkemy.explorandodisney.web.controller.exeptions.FieldAlreadyExistException;
import com.alkemy.explorandodisney.web.controller.exeptions.FieldInvalidException;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MovieSevice {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<MovieList> getAll(){
        return  movieRepository.getAll();
    }
    @Transactional(readOnly = true)
    public  List<MovieList> getByTitle(String title){
        List<MovieList> movies = movieRepository.getByTitle(title);
        if(movies.isEmpty()){
            throw new NoSuchElementException("There aren't any movies with the title like '"+title+"' ");
        }
        Collections.sort(movies,(x,y) -> x.getTitle().compareToIgnoreCase(y.getTitle()));
        return movies;
    }

    public boolean validCategory(String category){
        boolean validCategory = false;
        for (MovieGenre genre: MovieGenre.values()) {
            if(genre.getValor().equals(category)){
                validCategory = true;
            }
        }
        return validCategory;
    }
    @Transactional(readOnly = true)
    public  List<MovieList> getByCategory(String category){
        boolean validCategory = validCategory(category);

        if (validCategory == false){
            throw new FieldInvalidException("The category '"+category+"' doesn't exists");
        }
        List<MovieList> movies = movieRepository.getByCategory(category);
        if(movies.isEmpty()){
            throw new NoSuchElementException("There aren't any movies in the category'"+category+"' ");
        }
        Collections.sort(movies,(x,y) -> x.getTitle().compareToIgnoreCase(y.getTitle()));
        return movies;
    }
    @Transactional(readOnly = true)
    public List<MovieList> getByDate(String order){
        if(order.equals("ASC")){
            return movieRepository.getByFechaAsc();
        }else{
            return movieRepository.getByFechaDesc();
        }
    }

    @Transactional(readOnly = true)
    public Movie getById(Long idPelicula){
        Optional<Movie> movie = movieRepository.getDescriptionMovie(idPelicula);
        if (movie.isEmpty()){
            throw new NoSuchElementException("The movie with id "+idPelicula+" doesn't exists");
        }
        return movie.get();
    }

    @Transactional(readOnly = true)
    public Movie getByExactTitle(String title){
        Optional<Movie> movie = movieRepository.getByExcactTitle(title);
        if (movie.isEmpty()){
            throw new NoSuchElementException("The movie with id "+title+" doesn't exists");
        }
        return movie.get();
    }

    @Transactional
    public boolean createMovie(Movie movie){
        try {
            if(!movieRepository.getByExcactTitle(movie.getTitle()).isEmpty()){
                throw new FieldAlreadyExistException("The movie with title '"+movie.getTitle()+"' already exists");
            }
            boolean validCategory = validCategory(movie.getCategory());

            if (validCategory == false){
                throw new FieldInvalidException("The category '"+movie.getCategory()+"' doesn't exists");
            }
            String name = movie.getCategory();
            Category category = categoryRepository.getByName(name);
            movieRepository.createMovie(movie.getTitle(),movie.getCreationDate(),movie.getScore(),category.getIdCategory());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public Movie save (Movie movie){
        return movieRepository.save(movie);
    }


    @Transactional
    public boolean updateMovie(Long idPelicula,Movie movie){

        try {
            Optional<Movie> movieBeforeUpdate = movieRepository.getDescriptionMovie(idPelicula);
            if (movieBeforeUpdate.isEmpty()){
                throw new NoSuchElementException("The movie with id "+idPelicula+" doesn't exists");
            }
            boolean validCategory = validCategory(movie.getCategory());

            if (validCategory == false){
                throw new FieldInvalidException("The category '"+movie.getCategory()+"' doesn't exists");
            }
            String name = movie.getCategory();
            Category category = categoryRepository.getByName(name);
            movieRepository.updateMovie(movie.getTitle(),movie.getCreationDate(),movie.getScore(),category.getIdCategory(),idPelicula);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Transactional
    public void addCharacters(String title,Set<String> names ){
        Movie movie = getByExactTitle(title);
        Long idMovie = movie.getIdMovie();

        for(String name : names){
            Optional<Character> character = characterRepository.getByExcactName(name);
            if (character.isEmpty()){
                throw new NoSuchElementException("The character with the name '"+name+"' doesn't exists");
            }
            Long idCharacter = character.get().getIdCharacter();

            Integer existsRelation = characterRepository.existsRelation(idCharacter,idMovie);
            if(idCharacter != null && existsRelation==0){
                characterRepository.createRelationMovie(idCharacter,idMovie);
            }
        }
    }

    @Transactional
    public void removeCharacters(String title,Set<String> names){
        Movie movie = getByExactTitle(title);
        Long idMovie = movie.getIdMovie();

        for(String name : names){
            Optional<Character> character = characterRepository.getByExcactName(name);
            Long idCharacter = character.get().getIdCharacter();
            Integer existsRelation = characterRepository.existsRelation(idCharacter,idMovie);
            if(idCharacter != null && existsRelation>0){
                characterRepository.deleteRelationCharacterMovie(idCharacter,idMovie);
            }
        }
    }

    @Transactional
    public void updateImage(String image,Long idMovie){
        movieRepository.updateImage(image,idMovie);
    }

    @Transactional
    public boolean deleteByTitle(String title){
        try {
            Optional<Movie> movie = movieRepository.getByExcactTitle(title);
            if (movie.isEmpty()){
                throw new NoSuchElementException("The movie with title '"+title+"' doesn't exists");
            }
            movieRepository.deleteRelation(movie.get().getIdMovie());
            movieRepository.deleteByTitle(title);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Transactional
    public boolean deleteById(Long idMovie){
        try {
            Optional<Movie> movie = movieRepository.getDescriptionMovie(idMovie);
            if (movie.isEmpty()){
                throw new NoSuchElementException("The movie with id "+idMovie+" doesn't exists");
            }
            movieRepository.deleteByIdMovie(idMovie);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
