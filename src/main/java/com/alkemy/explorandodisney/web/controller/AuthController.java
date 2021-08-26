package com.alkemy.explorandodisney.web.controller;

import com.alkemy.explorandodisney.domain.UserVO;
import com.alkemy.explorandodisney.domain.dto.AuthenticationRequest;
import com.alkemy.explorandodisney.domain.dto.AuthenticationResponse;
import com.alkemy.explorandodisney.domain.repository.UserRepositoty;
import com.alkemy.explorandodisney.domain.service.UserService;
import com.alkemy.explorandodisney.util.EmailStructure;
import com.alkemy.explorandodisney.web.controller.exeptions.InvalidDataException;
import com.alkemy.explorandodisney.web.security.JWTUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@Api(tags = {"Login"})
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepositoty userRepositoty;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MessageSource messageSource;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            Optional<UserVO> userVO = userRepositoty.getByUsername(request.getUsername());
            EmailStructure.send(userVO.get().getMail(), jwt);
            return new AuthenticationResponse(jwt);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
            //return new ResponseEntity(new CustomErrorType("Invalid username or password"),HttpStatus.FORBIDDEN);
        } catch (IOException i) {
            throw new BadCredentialsException("Invalid username or password");
        } catch (InternalAuthenticationServiceException e) {
            throw new BadCredentialsException("Invalid username or password");//username doesn't exits
        }
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody @Valid UserVO user, BindingResult result){
        //StringBuilder builder = new StringBuilder();
        //result.getFieldErrors().stream().forEach(f -> builder.append(f.getField() + ": " + f.getDefaultMessage()+" "));
        String errorMsg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));

        if (result.hasErrors()){
            throw new InvalidDataException(errorMsg,result);
        }
        userService.createUser(user);
    }
}
