package com.your.cellar.book.controller;

import com.your.cellar.book.dto.BaseResponse;
import com.your.cellar.book.dto.RegionDto;
import com.your.cellar.book.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Set;

@RestController
@RequestMapping(path = "/regions")
public class RegionController {

    private RegionService regionService;
    private MessageSource messageSource;

    @Autowired
    public RegionController(RegionService regionService, MessageSource messageSource) {
        this.regionService = regionService;
        this.messageSource = messageSource;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Set<RegionDto>>> getAllRegions() {
        Set<RegionDto> regions = regionService.fetchAllRegions();

        if (regions != null) {
            BaseResponse<Set<RegionDto>> response = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("region.fetch.all.success", null, Locale.getDefault()),
                    regions
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<Set<RegionDto>> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("region.fetch.all.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<RegionDto>> addNewRegion(@RequestBody RegionDto regionRequestDto) {
        RegionDto regionDto = regionService.addNewRegion(regionRequestDto);

        if (regionDto != null) {
            BaseResponse<RegionDto> response = new BaseResponse<>(
                    HttpStatus.CREATED.value(),
                    messageSource.getMessage("region.addition.success", null, Locale.getDefault()),
                    regionDto
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            BaseResponse<RegionDto> response = new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage("region.addition.error", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
