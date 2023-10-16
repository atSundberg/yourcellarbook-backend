package com.your.cellar.book.controller;

import com.your.cellar.book.dto.BaseResponse;
import com.your.cellar.book.dto.GrapeDto;
import com.your.cellar.book.entity.Grape;
import com.your.cellar.book.service.GrapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Set;

@RestController
@RequestMapping("/grapes")
public class GrapeController {

    private GrapeService grapeService;
    private MessageSource messageSource;

    @Autowired
    public GrapeController(GrapeService grapeService, MessageSource messageSource) {
        this.grapeService = grapeService;
        this.messageSource = messageSource;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Set<Grape>>> getAllGrapes() {
        Set<Grape> grapes = grapeService.fetchAllGrapes();

        if (grapes != null) {
            BaseResponse<Set<Grape>> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("grape.fetch.all.success", null, Locale.getDefault()),
                    grapes
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<Set<Grape>> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("grape.fetch.all.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<GrapeDto>> addNewGrape(@RequestBody GrapeDto grapeRequestDto) {
        GrapeDto grapeDto = grapeService.addNewGrape(grapeRequestDto);
        if (grapeDto != null) {
            BaseResponse<GrapeDto> response = new BaseResponse<>(
                    HttpStatus.CREATED.value(),
                    messageSource.getMessage("grape.addition.success", null, Locale.getDefault()),
                    grapeDto
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            BaseResponse<GrapeDto> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("grape.addition.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
