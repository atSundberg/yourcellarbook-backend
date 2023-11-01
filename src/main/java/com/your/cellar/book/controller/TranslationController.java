package com.your.cellar.book.controller;

import com.your.cellar.book.dto.BaseResponse;
import com.your.cellar.book.service.TranslationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Properties;

@Slf4j
@RestController
@RequestMapping("/translations")
public class TranslationController {

    private TranslationService translationService;
    private MessageSource messageSource;

    @Autowired
    public TranslationController(TranslationService translationService, MessageSource messageSource) {
        this.translationService = translationService;
        this.messageSource = messageSource;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Properties>> getTranslation(@RequestParam String countryCode) {
        Properties properties = translationService.fetchProperties(countryCode);

        if (properties != null) {
            BaseResponse<Properties> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("translation.fetch.success", null, Locale.getDefault()),
                    properties
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<Properties> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("translation.fetch.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
