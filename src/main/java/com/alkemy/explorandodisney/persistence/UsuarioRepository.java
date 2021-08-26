package com.alkemy.explorandodisney.persistence;

import com.alkemy.explorandodisney.domain.UserVO;
import com.alkemy.explorandodisney.domain.repository.UserRepositoty;
import com.alkemy.explorandodisney.persistence.crud.UsuarioCrudRepository;
import com.alkemy.explorandodisney.persistence.entity.Usuario;
import com.alkemy.explorandodisney.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepositoty {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(UserVO userVO) {
        usuarioCrudRepository.save(userMapper.toUsuario(userVO));
    }

    @Override
    public Optional<UserVO> getByUsername(String username){
       return usuarioCrudRepository.findByUsuario(username).map(usuario ->userMapper.toUser(usuario));
    }

    @Override
    public Optional<UserVO> getByMail(String mail) {
        return usuarioCrudRepository.findByMail(mail).map(usuario ->userMapper.toUser(usuario));
    }

    @Override
    public void update(String username, String password, String mail) {
        usuarioCrudRepository.upadte(username,password,mail);
    }

    @Override
    public void deleteByMail(String mail) {
        usuarioCrudRepository.deleteByMail(mail);
    }

/*    @Override
    public boolean existsMail(String mail) {
       return  usuarioCrudRepository.existsMail(mail);
    }

    @Override
    public boolean existsUsername(String username) {
        return usuarioCrudRepository.existsUser(username);
    }*/

}
