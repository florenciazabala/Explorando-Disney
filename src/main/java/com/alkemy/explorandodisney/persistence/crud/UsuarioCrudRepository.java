package com.alkemy.explorandodisney.persistence.crud;

import com.alkemy.explorandodisney.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsuarioCrudRepository extends CrudRepository<Usuario,String> {

    Optional<Usuario> findByUsuario(String usuario);
    Optional<Usuario> findByMail(String mail);

    @Modifying
    @Query("update Usuario u set u.usuario = :usuario, u.clave = :clave where u.mail= :mail")
    void upadte(@Param("usuario") String usuario, @Param("clave") String clave,@Param("mail") String mail);

    @Modifying
    void deleteByMail(String mail);

    //boolean existsMail(String mail);
    //boolean existsUser(String user);
}
