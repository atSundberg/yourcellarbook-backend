package com.your.cellar.book.controller;

import com.your.cellar.book.dto.BaseResponse;
import com.your.cellar.book.dto.request.UserRequestModel;
import com.your.cellar.book.dto.response.UserResponseModel;
import com.your.cellar.book.entity.User;
import com.your.cellar.book.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.Locale;
import java.util.Set;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(UserController.class.getName());

    @Autowired
    public UserController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

/*    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<String>> getUserResponse() {
        BaseResponse<String> response =
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Working user response",
                        "I AM ADAM"
                );

        logger.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<BaseResponse<Set<User>>> getAllUsers() {
        Set<User> users = userService.fetchAllUsers();

        if (users != null) {
            BaseResponse<Set<User>> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("user.fetch.all.success", null, Locale.getDefault()),
                    users
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<Set<User>> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("user.fetch.all.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/signup",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<UserResponseModel>> createUser(@RequestBody UserRequestModel userRequestModel) throws RoleNotFoundException {
        logger.info("Input user: {}", userRequestModel);

        UserResponseModel userResponseModel = userService.createUser(userRequestModel);

        if (userResponseModel != null) {
            BaseResponse<UserResponseModel> response = new BaseResponse<>(
                    HttpStatus.CREATED.value(),
                    messageSource.getMessage("user.creation.success", null, Locale.getDefault()),
                    userResponseModel
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            BaseResponse<UserResponseModel> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("user.creation.error", null, Locale.getDefault()),
                    null
                    );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }




}
