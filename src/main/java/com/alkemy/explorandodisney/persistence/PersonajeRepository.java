package com.alkemy.explorandodisney.persistence;

import com.alkemy.explorandodisney.domain.Character;
import com.alkemy.explorandodisney.domain.CharacterList;
import com.alkemy.explorandodisney.domain.repository.CharacterRepository;
import com.alkemy.explorandodisney.persistence.crud.PersonajeCrudRepository;
import com.alkemy.explorandodisney.persistence.entity.Personaje;
import com.alkemy.explorandodisney.persistence.mapper.CharacterListMapper;
import com.alkemy.explorandodisney.persistence.mapper.CharacterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonajeRepository implements CharacterRepository {

    @Autowired
    private PersonajeCrudRepository personajeCrudRepository;

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterListMapper characterListMapper;



    //Read
    @Override
    public List<CharacterList> getAll() {
        List <Personaje> personajes  = personajeCrudRepository.findAll();
        //List<Character> characters = characterMapper.toCharacters(personajes);
        return characterListMapper.toCharactersList(personajes);
    }
    @Override
    public List<CharacterList> getByName(String name) {
        List<Personaje> personajes = personajeCrudRepository.findByNombreContaining(name);
        return characterListMapper.toCharactersList(personajes);
    }


    @Override
    public List<CharacterList> getByAge(Integer youngerAge,Integer olderAge) {
        List<Personaje> personajes = personajeCrudRepository.findByEdadBetween(youngerAge,olderAge);
        return characterListMapper.toCharactersList(personajes);
    }

    @Override
    public List<CharacterList> getByWeight(Double lowerWeight,Double higherWeight) {
        List<Personaje> personajes = personajeCrudRepository.findByPesoBetween(lowerWeight,higherWeight);
        return characterListMapper.toCharactersList(personajes);
    }

    @Override
    public List<CharacterList> getByMovies(String title) {
        List<Personaje> personajes = personajeCrudRepository.findByMovie(title);
        return characterListMapper.toCharactersList(personajes);
    }

    @Override
    public Optional<Character> getById(Long idCharacter) {

        return personajeCrudRepository.findByIdPersonaje(idCharacter).map(personaje ->characterMapper.toCharacter(personaje) );
    }

    @Override
    public Optional<Character> getByExcactName(String name) {
        return personajeCrudRepository.findByNombre(name).map(personaje -> characterMapper.toCharacter(personaje));
    }

    //Create

    @Override
    public void createCharacter(String name, Integer age, Double weight, String story) {
        personajeCrudRepository.save(name,age,weight,story);
    }

    //Update

    @Override
    public void updateCharacter(String name, Integer age, Double weight, String story, Long id) {
        personajeCrudRepository.updateCharacter(name,age,weight,story,id);
    }

    @Override
    public void updateCharacterImage(String picture, Long idCharacter) {
        personajeCrudRepository.updateCharacterImage(picture, idCharacter);
    }


    //Delete
    @Override
    public void deleteByNameCharacter(String name){
        personajeCrudRepository.deleteByNombre(name);
    }

    @Override
    public void deleteByIdCharacter(Long idCharacter) {
        personajeCrudRepository.deleteByIdPersonaje(idCharacter);
    }


    //Relation
    @Override
    public Integer existsRelation(Long idCharacter, Long idMovie) {
        return personajeCrudRepository.exists(idCharacter,idMovie);
    }

    @Override
    public void createRelationMovie(Long idCharacter, Long idMovie) {
        personajeCrudRepository.saveRelation(idCharacter,idMovie);
    }

    @Override
    public void deleteRelation(Long idCharacter) {
        personajeCrudRepository.deleteRelation(idCharacter);
    }

    @Override
    public void deleteRelationCharacterMovie(Long idPersonaje, Long idPelicula) {
        personajeCrudRepository.deleteRelationCharacterMovie(idPersonaje,idPelicula);
    }
}
