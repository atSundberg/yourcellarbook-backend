package com.your.cellar.book.controller;

import com.your.cellar.book.dto.BaseResponse;
import com.your.cellar.book.dto.UserWineDto;
import com.your.cellar.book.repository.UserWineRepository;
import com.your.cellar.book.service.UserWineService;
import com.your.cellar.book.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Set;

@RestController
@RequestMapping(path = "/user/wine")
public class UserWineController {

    private MessageSource messageSource;
    private UserWineService userWineService;
    private WineService wineService;

    @Autowired
    public UserWineController(MessageSource messageSource, UserWineService userWineService, WineService wineService) {
        this.messageSource = messageSource;
        this.userWineService = userWineService;
        this.wineService = wineService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Set<UserWineDto>>> getAllWinesByUser(@PathVariable Long id) {
        Set<UserWineDto> userWines = userWineService.fetchAllUserWines(id);

        if (userWines != null) {
            BaseResponse<Set<UserWineDto>> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("user.wine.fetch.all.success", null, Locale.getDefault()),
                    userWines
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<Set<UserWineDto>> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("user.wine.fetch.all.error", null, Locale.getDefault()),
                    null
                    );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<UserWineDto>> addNewUserWine(@RequestBody UserWineDto userWineRequestDto) {
        UserWineDto userWineDto = userWineService.createNewUserWine(userWineRequestDto);

        if (userWineDto != null) {
            BaseResponse<UserWineDto> response = new BaseResponse<>(
                    HttpStatus.CREATED.value(),
                    messageSource.getMessage("user.wine.addition.success", null, Locale.getDefault()),
                    userWineDto
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            BaseResponse<UserWineDto> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("user.wine.addition.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping(path = "/info", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<UserWineDto>> updateWineInformation(@RequestBody UserWineDto userWineRequestDto) {
        UserWineDto userWineDto = userWineService.updateUserWineInformation(userWineRequestDto);

        if (userWineDto != null) {
            BaseResponse<UserWineDto> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("user.wine.information.update.success", null, Locale.getDefault()),
                    userWineDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<UserWineDto> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("user.wine.information.update.error", null, Locale.getDefault()),
                    null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/quantity", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<UserWineDto>> updateWineQuantity(@RequestBody UserWineDto userWineRequestDto) {
        UserWineDto userWineDto = userWineService.updateWineQuantity(userWineRequestDto);

        if (userWineDto != null) {
            BaseResponse<UserWineDto> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("user.wine.quantity.update.success", null, Locale.getDefault()),
                    userWineDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<UserWineDto> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("user.wine.quantity.update.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
