package com.alkemy.explorandodisney.domain.service;

import com.alkemy.explorandodisney.domain.UserVO;
import com.alkemy.explorandodisney.domain.repository.UserRepositoty;
import com.alkemy.explorandodisney.web.controller.exeptions.FieldAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepositoty userRepositoty;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserVO> us = userRepositoty.getByUsername(username);
        return new User(""+us.get().getUsername(),"{noop}"+us.get().getPassword(),new ArrayList<>());
    }

    @Transactional
    public boolean createUser(UserVO user){
        try{
            if(!userRepositoty.getByMail(user.getMail()).isEmpty()){
                throw new FieldAlreadyExistException("The email account '"+user.getMail()+"'is already registered");
            }
            if(!userRepositoty.getByUsername(user.getUsername()).isEmpty()){
                throw new FieldAlreadyExistException(("username '"+user.getUsername()+"' already exists"));
            }
            /*if(user.getPassword().length()<4){
                throw new IllegalArgumentException("The password must contain at least 4 characters");
            }*/
            userRepositoty.save(user);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

}
