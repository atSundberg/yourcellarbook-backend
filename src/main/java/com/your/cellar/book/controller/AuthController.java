/*
package com.your.cellar.book.controller;

import com.your.cellar.book.model.LoginRequest;
import com.your.cellar.book.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(@RequestBody LoginRequest userLogin) {
        LOG.info("Token requested for user: '{}'", userLogin);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()));
        String token = tokenService.generateToken(authentication);
        LOG.info("Token granted {}", token);
        return token;
    }
}

*/
