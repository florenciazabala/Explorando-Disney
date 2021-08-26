package com.alkemy.explorandodisney.domain.repository;

import com.alkemy.explorandodisney.domain.UserVO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public interface UserRepositoty {
    void save(UserVO userVO);
    Optional<UserVO> getByUsername(String username);
    Optional<UserVO> getByMail(String mail);
    void update(String username, String password, String mail);
    void deleteByMail(String mail);
    //boolean existsMail(String mail);
    //boolean existsUsername(String username);
}
