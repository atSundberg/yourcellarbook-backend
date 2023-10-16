package com.your.cellar.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RegionDto {

    @JsonProperty("regionId")
    private Long regionId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("country")
    private String country;
}
