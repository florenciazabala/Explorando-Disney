package com.alkemy.explorandodisney.domain.service;

import com.alkemy.explorandodisney.domain.Character;
import com.alkemy.explorandodisney.domain.CharacterList;
import com.alkemy.explorandodisney.domain.Movie;
import com.alkemy.explorandodisney.domain.repository.CharacterRepository;
import com.alkemy.explorandodisney.domain.repository.MovieRepository;
import com.alkemy.explorandodisney.web.controller.exeptions.FieldAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<CharacterList> getAll(){
       return characterRepository.getAll();
   }
    @Transactional(readOnly = true)
    public List<CharacterList> getByName(String name){
        List<CharacterList> charactersLists = characterRepository.getByName(name);
        if (charactersLists.isEmpty()){
            throw new NoSuchElementException("There aren't any characters with the name like '"+name+"' ");
        }
        Collections.sort(charactersLists,(x, y) -> x.getName().compareToIgnoreCase(y.getName()));
        return charactersLists;
    }
    @Transactional(readOnly = true)
    public List<CharacterList>getByAge(Integer youngerAge, Integer olderAge){
        List<CharacterList> charactersLists = characterRepository.getByAge(youngerAge,olderAge);
        if (charactersLists.isEmpty()){
            throw new NoSuchElementException("There aren't any characters with ages between "+youngerAge+" and "+olderAge+" years");
        }
        Collections.sort(charactersLists,(x, y) -> x.getName().compareToIgnoreCase(y.getName()));
        return charactersLists;
    }
    @Transactional(readOnly = true)
    public List<CharacterList> getByWeight(Double lowerWeight, Double higherWeight){
        List<CharacterList> charactersLists = characterRepository.getByWeight(lowerWeight,higherWeight);
        if (charactersLists.isEmpty()){
            throw new NoSuchElementException("There aren't any characters with weights between "+lowerWeight+" and "+higherWeight+" years");
        }
        Collections.sort(charactersLists,(x, y) -> x.getName().compareToIgnoreCase(y.getName()));
        return charactersLists;
    }
    @Transactional(readOnly = true)
    public List<CharacterList>getByMovies(String title){
        Optional<Movie> movie = movieRepository.getByExcactTitle(title);
        if(movie.isEmpty() ){
            throw new NoSuchElementException("The movie with the title '"+title+"' doesn't exists");
        }
        List<CharacterList> charactersLists = characterRepository.getByMovies(title);
        if (charactersLists.isEmpty()){
            throw new NoSuchElementException("There aren't any characters associated whith the movie '"+title+"'" );
        }
        Collections.sort(charactersLists,(x, y) -> x.getName().compareToIgnoreCase(y.getName()));
        return charactersLists;
    }
    @Transactional(readOnly = true)
    public Character getById(Long idCharacter){
        Optional<Character> character = characterRepository.getById(idCharacter);
        if (character.isEmpty()){
            throw new NoSuchElementException("The character with id "+idCharacter+" doesn't exists");
        }
       return character.get();
    }
    @Transactional(readOnly = true)
    public Character getByExactName(String name){
        Optional<Character> character = characterRepository.getByExcactName(name);
        if (character.isEmpty()){
            throw new NoSuchElementException("The character with the name '"+name+"' doesn't exists");
        }
        return character.get();
    }

    @Transactional
    public  boolean createCharacter(Character character){
        try {
            if(!characterRepository.getByExcactName(character.getName()).isEmpty()){
                throw new FieldAlreadyExistException("The character with name '"+character.getName()+"' already exists");
            }

            characterRepository.createCharacter(character.getName(),character.getAge(),character.getWeight(),character.getStory());
            Set<String> titles = character.getTitles();
            Character ch = getByExactName(character.getName());
            Long idCharacter = ch.getIdCharacter();
            addMovies(titles,idCharacter);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Transactional
    public  boolean updateCharacter(Long idCharacter,Character character){
        try {
            //Get the titles before update and compare to titles after update

            Optional<Character> characterBeforeUpdate = characterRepository.getById(idCharacter);
            if(characterBeforeUpdate.isEmpty()){
                throw new NoSuchElementException("The character with id "+idCharacter+" doesn't exists");
            }
            Set<String> titlesToRemove =  characterBeforeUpdate.get().getTitles();
            Set<String> titlesToAdd = character.getTitles();

            if(titlesToAdd !=null && titlesToRemove!=null){
                titlesToRemove.removeAll(character.getTitles());
                titlesToAdd.removeAll(titlesToRemove);
            }
            characterRepository.updateCharacter(character.getName(),character.getAge(),character.getWeight(),character.getStory(),idCharacter);
            if(titlesToAdd != null){
                addMovies(titlesToAdd,idCharacter);
            }
            if(titlesToRemove != null){
                removeMovies(titlesToRemove, idCharacter);
            }
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }

    }

    @Transactional
    public void addMovies(Set<String> titles, Long idCharacter ){

        for(String title : titles){
            Optional<Movie> movie = movieRepository.getByExcactTitle(title);
            if(movie.isEmpty() ){
                throw new NoSuchElementException("The movie with the title '"+title+"' doesn't exists");
            }
            Long idMovie = movie.get().getIdMovie();

            Integer existsRelation = characterRepository.existsRelation(idCharacter,idMovie);
            if(idMovie != null && existsRelation==0){
                characterRepository.createRelationMovie(idCharacter,idMovie);
            }
        }
    }

    @Transactional
    public void removeMovies(Set<String> titles, Long idCharacter ){

        for(String title : titles){
            Optional<Movie> movie = movieRepository.getByExcactTitle(title);
            Long idMovie = movie.get().getIdMovie();
            Integer existsRelation = characterRepository.existsRelation(idCharacter,idMovie);
            if(idMovie != null && existsRelation>0){
                characterRepository.deleteRelationCharacterMovie(idCharacter,idMovie);
            }
        }
    }



    @Transactional
    public void updateCharacterImage(String picture, Long idCharacter){
        characterRepository.updateCharacterImage(picture,idCharacter);
    }
    public boolean deleteByIdCharacter(Long idCharacter){
        try {
            characterRepository.deleteRelation(idCharacter);
            characterRepository.deleteByIdCharacter(idCharacter);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Transactional
    public boolean deleteByName(String name){
        try {
            Optional<Character> character = characterRepository.getByExcactName(name);
            if (character.isEmpty()){
                throw new NoSuchElementException("The character with the name '"+name+"' doesn't exists");
            }
            Long idCharacter = character.get().getIdCharacter();
            characterRepository.deleteRelation(idCharacter);
            characterRepository.deleteByNameCharacter(name);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}


