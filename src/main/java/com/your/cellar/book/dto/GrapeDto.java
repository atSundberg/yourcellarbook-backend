package com.your.cellar.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
public class GrapeDto {

    @JsonProperty("grape_id")
    private Long grapeId;

    @JsonProperty("name")
    private String name;
}
