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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping
    public ResponseEntity<BaseResponse<Set<User>>> getAllUsers() {
        Set<User> users = userService.fetchAllUsers();

        if (users != null) {
            BaseResponse<Set<User>> response = new BaseResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.fetch.all.success", null, Locale.getDefault()), users);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<Set<User>> response = new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), messageSource.getMessage("user.fetch.all.error", null, Locale.getDefault()), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<UserResponseModel>> getUserByUsername(@PathVariable String username) {
        UserResponseModel userResponseModel = userService.fetchUserByUsername(username);

        if (userResponseModel != null) {
            BaseResponse<UserResponseModel> response = new BaseResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.fetch.by.username.success", null, Locale.getDefault()), userResponseModel);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<UserResponseModel> response = new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), messageSource.getMessage("user.fetch.by.username.error", null, Locale.getDefault()), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<UserResponseModel>> createUser(@RequestBody UserRequestModel userRequestModel) throws RoleNotFoundException {
        logger.info("Input user: {}", userRequestModel);

        UserResponseModel userResponseModel = userService.createUser(userRequestModel);

        if (userResponseModel != null) {
            BaseResponse<UserResponseModel> response = new BaseResponse<>(HttpStatus.CREATED.value(), messageSource.getMessage("user.creation.success", null, Locale.getDefault()), userResponseModel);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            BaseResponse<UserResponseModel> response = new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), messageSource.getMessage("user.creation.error", null, Locale.getDefault()), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/winelist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<UserResponseModel>> updateShowWineList(@RequestBody UserRequestModel userRequestModel) {

        UserResponseModel userResponseModel = userService.updateShowWineListStatus(userRequestModel);

        if (userResponseModel != null) {
            BaseResponse<UserResponseModel> response = new BaseResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.update.show.wines.success", null, Locale.getDefault()), userResponseModel);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<UserResponseModel> response = new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), messageSource.getMessage("user.update.show.wines.error", null, Locale.getDefault()), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<BaseResponse<UserResponseModel>> deleteUser(@RequestBody UserRequestModel userRequestModel) {
        UserResponseModel userResponseModel = userService.deleteUser(userRequestModel);

        if (userResponseModel != null) {
            BaseResponse<UserResponseModel> response = new BaseResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.delete.success", null, Locale.getDefault()), userResponseModel);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<UserResponseModel> response = new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), messageSource.getMessage("user.delete.error", null, Locale.getDefault()), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


}
