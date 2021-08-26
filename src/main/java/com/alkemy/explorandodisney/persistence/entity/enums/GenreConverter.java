package com.alkemy.explorandodisney.persistence.entity.enums;

import com.alkemy.explorandodisney.persistence.entity.enums.MovieGenre;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GenreConverter implements AttributeConverter<MovieGenre,String>{

    @Override
    public String convertToDatabaseColumn(MovieGenre genero) {
        if(genero == null){
            return null;
        }
        return genero.getValor();
    }


    @Override
    public MovieGenre convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }
        return Stream.of(MovieGenre.values())
                .filter(g -> g.getValor().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
