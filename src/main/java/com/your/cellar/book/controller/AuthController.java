package com.your.cellar.book.controller;

import com.your.cellar.book.dto.AuthenticationResponse;
import com.your.cellar.book.dto.BaseResponse;
import com.your.cellar.book.dto.request.UserRequestModel;
import com.your.cellar.book.service.TokenService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/token")
public class AuthController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private MessageSource messageSource;

    @Autowired
    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, MessageSource messageSource) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.messageSource = messageSource;
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<AuthenticationResponse>> token(@RequestBody UserRequestModel userRequestModel) throws AuthenticationException {
        log.info("User to authenticate: {}", userRequestModel);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequestModel.getUsername(), userRequestModel.getPassword()));
        if (authentication.isAuthenticated()) {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse(tokenService.generateToken(authentication));
            BaseResponse<AuthenticationResponse> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("token.generated.successfully", null, Locale.getDefault()),
                    authenticationResponse
            );
            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            throw new UsernameNotFoundException("User not found");


        }
    }

}

