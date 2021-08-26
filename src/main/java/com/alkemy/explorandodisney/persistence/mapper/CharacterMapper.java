package com.alkemy.explorandodisney.persistence.mapper;

import com.alkemy.explorandodisney.domain.Character;
import com.alkemy.explorandodisney.persistence.entity.Pelicula;
import com.alkemy.explorandodisney.persistence.entity.Personaje;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",uses={MovieMapper.class})
public interface CharacterMapper {
    @Mappings({
            @Mapping(source = "idPersonaje",target = "idCharacter"),
            @Mapping(source = "imagen",target = "picture"),
            @Mapping(source = "nombre",target = "name"),
            @Mapping(source = "edad",target = "age"),
            @Mapping(source = "peso",target = "weight"),
            @Mapping(source = "historia",target = "story"),
            @Mapping(source = "peliculas",target = "titles")
    })
    Character toCharacter(Personaje personaje);
    List<Character> toCharacters(List<Personaje> peronajes);

    @InheritInverseConfiguration
    @Mapping(target="peliculas",ignore = true)
    Personaje toPersonaje(Character character);
    List<Personaje> toPersonajes(List<Character> characters);

    default String fromMovie(Pelicula pelicula) {
        return pelicula == null ? null : pelicula.getTitulo();
    }

}
