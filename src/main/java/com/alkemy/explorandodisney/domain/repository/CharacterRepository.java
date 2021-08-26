package com.alkemy.explorandodisney.domain.repository;

import com.alkemy.explorandodisney.domain.Character;
import com.alkemy.explorandodisney.domain.CharacterList;
import com.alkemy.explorandodisney.persistence.entity.Personaje;


import java.util.List;
import java.util.Optional;

public interface CharacterRepository {
    void createCharacter(String name,Integer age,Double weight,String story);
    //List<CharacterList> getAll();
    List<CharacterList> getAll();
    List<CharacterList> getByName(String name);
    List<CharacterList> getByAge(Integer youngerAge, Integer olderAge);
    List<CharacterList> getByWeight(Double lowerWeight, Double higherWeight);
    List<CharacterList> getByMovies(String title);
    Optional<Character> getById(Long idCharacter);
    Optional<Character> getByExcactName(String name);
    void updateCharacter(String name,Integer age,Double weight,String story,Long id);
    void updateCharacterImage(String picture, Long idCharacter);
    void deleteByIdCharacter(Long idCharacter);
    void deleteByNameCharacter(String name);

    Integer existsRelation(Long idCharacter, Long idMovie);
    void createRelationMovie(Long idCharacter, Long idMovie);
    void deleteRelation(Long idCharacter);
    void deleteRelationCharacterMovie(Long idPersonaje,Long idPelicula);
}
