package com.your.cellar.book.controller;

import com.your.cellar.book.dto.BaseResponse;
import com.your.cellar.book.dto.ProducerDto;
import com.your.cellar.book.service.ProducerService;
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
@RequestMapping("/producers")
public class ProducerController {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerController.class);
    private MessageSource messageSource;
    private ProducerService producerService;

    @Autowired
    public ProducerController(MessageSource messageSource, ProducerService producerService) {
        this.messageSource = messageSource;
        this.producerService = producerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Set<ProducerDto>>> getAllProducers() {
        Set<ProducerDto> producers = producerService.getAllProducers();

        if (producers != null) {
            BaseResponse<Set<ProducerDto>> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("producer.fetch.all.success", null, Locale.getDefault()),
                    producers
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<Set<ProducerDto>> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("producer.fetch.all.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<ProducerDto>> addNewProducer(@RequestBody ProducerDto producerRequestDto) {
        ProducerDto producerDto = producerService.addNewProducer(producerRequestDto);
        if (producerDto != null) {
            BaseResponse<ProducerDto> response = new BaseResponse<>(
                    HttpStatus.CREATED.value(),
                    messageSource.getMessage("producer.addition.success", null, Locale.getDefault()),
                    producerDto
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            BaseResponse<ProducerDto> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("producer.addition.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
