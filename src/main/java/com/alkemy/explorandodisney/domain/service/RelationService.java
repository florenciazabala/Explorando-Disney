package com.alkemy.explorandodisney.domain.service;

import com.alkemy.explorandodisney.domain.Character;
import com.alkemy.explorandodisney.domain.Movie;
import com.alkemy.explorandodisney.domain.repository.CharacterRepository;
import com.alkemy.explorandodisney.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RelationService {
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MovieRepository movieRepository;

/*
    @Transactional
    public boolean deleteMovieCharacter(String name,String title){
        try{
            Character character = characterRepository.getByExcactName(name);
            Long idCharacter = character.getIdCharacter();
            Movie movie = movieRepository.getByExcactTitle(title);
            Long idMovie = movie.getIdMovie();
            characterRepository.deleteRelationCharacterMovie(idCharacter,idMovie);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Transactional
    public void addMovieCharacter(String title, String nameCharacter){
        Character character = characterRepository.getByExcactName(nameCharacter);
        Long idCharacter = character.getIdCharacter();

        Movie movie = movieRepository.getByExcactTitle(title);
        Long idMovie = movie.getIdMovie();
        if(idMovie != null && idCharacter != null){
            characterRepository.createRelationMovie(idCharacter,idMovie);
        }
    }*/

    //Implemento cada una en el controller /{id}/add?title=
}
