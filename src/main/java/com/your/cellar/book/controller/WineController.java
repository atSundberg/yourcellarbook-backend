package com.your.cellar.book.controller;

import com.your.cellar.book.dto.BaseResponse;
import com.your.cellar.book.dto.GrapeDto;
import com.your.cellar.book.dto.request.WineRequestModel;
import com.your.cellar.book.dto.response.WineResponseModel;
import com.your.cellar.book.service.WineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Set;

@RestController
@RequestMapping("/wines")
public class WineController {

    private static final Logger LOG = LoggerFactory.getLogger(WineController.class.getName());

    MessageSource messageSource;

    private WineService wineService;

    @Autowired
    public WineController(MessageSource messageSource, WineService wineService) {
        this.messageSource = messageSource;
        this.wineService = wineService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Set<WineResponseModel>>> getAllWines() {
        Set<WineResponseModel> wines = wineService.fetchAllWines();

        if (wines != null) {

            BaseResponse<Set<WineResponseModel>> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    "Successfully fetched all wines.",
                    wines
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {

            BaseResponse<Set<WineResponseModel>> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error fetching all wines.",
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<WineResponseModel>> addWine(@RequestBody WineRequestModel wineRequestModel) {
        LOG.info("Wine to add: {}", wineRequestModel);
        WineResponseModel wineResponseModel = wineService.addWine(wineRequestModel);

        if (wineResponseModel != null) {
            BaseResponse<WineResponseModel> response = new BaseResponse<>(
                    HttpStatus.CREATED.value(),
                    messageSource.getMessage("wine.addition.success", null, Locale.getDefault()),
                    wineResponseModel
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            BaseResponse<WineResponseModel> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("wine.addition.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/grapes/{id}")
    public ResponseEntity<BaseResponse<WineResponseModel>> updateWineGrapeById(@PathVariable Long id, @RequestBody List<GrapeDto> grapeRequestDto) {
        WineResponseModel wineResponseModel = wineService.updateWineGrape(id, grapeRequestDto);

        if (wineResponseModel != null) {
            BaseResponse<WineResponseModel> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("wine.update.grape.success", null, Locale.getDefault()),
                    wineResponseModel
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        BaseResponse<WineResponseModel> response = new BaseResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                messageSource.getMessage("wine.update.grape.error", null, Locale.getDefault()),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
