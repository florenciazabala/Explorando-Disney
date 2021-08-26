package com.alkemy.explorandodisney.persistence.mapper;

import com.alkemy.explorandodisney.domain.UserVO;
import com.alkemy.explorandodisney.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.io.IOException;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "usuario",target = "username"),
            @Mapping(source = "clave",target = "password")
    })
    UserVO toUser(Usuario usuario);

    @InheritInverseConfiguration
    Usuario toUsuario(UserVO userVO);
}
