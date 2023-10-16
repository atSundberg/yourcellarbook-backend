package com.your.cellar.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProducerDto {

    @JsonProperty("producerId")
    private Long producerId;

    @JsonProperty("name")
    private String name;
}
