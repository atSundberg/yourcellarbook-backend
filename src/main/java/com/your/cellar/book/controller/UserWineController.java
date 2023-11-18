package com.your.cellar.book.controller;

import com.your.cellar.book.dto.BaseResponse;
import com.your.cellar.book.dto.UserWineDto;
import com.your.cellar.book.dto.UserWinePublicDto;
import com.your.cellar.book.service.UserLogService;
import com.your.cellar.book.service.UserWineService;
import com.your.cellar.book.service.WineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(WineController.class.getName());
    private MessageSource messageSource;
    private UserWineService userWineService;
    private WineService wineService;
    private UserLogService userLogService;

    @Autowired
    public UserWineController(MessageSource messageSource, UserWineService userWineService, WineService wineService, UserLogService userLogService) {
        this.messageSource = messageSource;
        this.userWineService = userWineService;
        this.wineService = wineService;
        this.userLogService = userLogService;
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
        LOG.info("UserWine to add: {}", userWineRequestDto);
        UserWineDto userWineDto = userWineService.createNewUserWine(userWineRequestDto);

        if (userWineDto != null) {
            BaseResponse<UserWineDto> response = new BaseResponse<>(
                    HttpStatus.CREATED.value(),
                    messageSource.getMessage("user.wine.addition.success", null, Locale.getDefault()),
                    userWineDto
            );
            userLogService.logDataModification(userWineDto.getUser().getUsername(), "add-new-userwine");
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

    @PutMapping(path = "/isPublic", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<UserWineDto>> updatePublicStatus(@RequestBody UserWineDto userWineRequestDto) {
        UserWineDto userWineDto = userWineService.updateUserWinePublicStatus(userWineRequestDto);

        if (userWineDto != null) {
            BaseResponse<UserWineDto> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("user.wine.public.status.update.success", null, Locale.getDefault()),
                    userWineDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<UserWineDto> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("user.wine.public.status.update.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Set<Set<UserWinePublicDto>>>> getAllPublicWines() {
        LOG.info("Inside GetAllPublicWines");
        Set<Set<UserWinePublicDto>> userWineDtoSets = userWineService.fetchAllPublicWines();

        if (userWineDtoSets != null) {
            BaseResponse<Set<Set<UserWinePublicDto>>> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("user.wine.fetch.all.public.success", null, Locale.getDefault()),
                    userWineDtoSets);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<Set<Set<UserWinePublicDto>>> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("user.wine.fetch.all.public.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/drink", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<UserWineDto>> drinkWine(@RequestBody UserWineDto userWineRequestDto) {
        UserWineDto userWineDto = userWineService.handleDrinkWine(userWineRequestDto);

        if (userWineDto != null) {
            BaseResponse<UserWineDto> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("user.wine.drink.success", null, Locale.getDefault()),
                    userWineDto
            );
            userLogService.logDataModification(userWineDto.getUser().getUsername(), "drink-wine");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<UserWineDto> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("user.wine.drink.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
