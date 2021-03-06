package com.camomila.controller;

import static org.springframework.http.ResponseEntity.ok;
import com.camomila.repository.UserRepository;
import com.camomila.security.AccountCredentialsVO;
import com.camomila.security.jwt.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "AuthenticationEndpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository repository;

    @ApiOperation(value = "Authenticate a user by credentials")
    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/singin", produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity singin(@RequestBody AccountCredentialsVO data){
        try {
            var username = data.getUsername();
            var password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);

            var token = "";

            if(user != null){
                token = tokenProvider.createToken(username, user.getRoles());
            }else{
                throw new UsernameNotFoundException("Username " + username + " not found.");
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);

            return ok(model);
        }catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid Username/Password supplied!");
        }

    }
}
