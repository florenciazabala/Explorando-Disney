package com.alkemy.explorandodisney.persistence.mapper;

import com.alkemy.explorandodisney.domain.CharacterList;
import com.alkemy.explorandodisney.persistence.entity.Personaje;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CharacterListMapper {
    @Mappings({
            @Mapping(source = "imagen", target = "picture"),
            @Mapping(source = "nombre", target = "name")
    })
    CharacterList toCharacterList(Personaje personaje);
    List<CharacterList> toCharactersList(List<Personaje> personajes);

    @InheritInverseConfiguration
    @Mapping(target="idPersonaje",ignore = true)
    @Mapping(target="edad",ignore = true)
    @Mapping(target="peso",ignore = true)
    @Mapping(target="historia",ignore = true)
    @Mapping(target="peliculas",ignore = true)
    Personaje toPersonaje(CharacterList characterList);
    List<Personaje> toPersonajes(List<CharacterList> characters);
}
