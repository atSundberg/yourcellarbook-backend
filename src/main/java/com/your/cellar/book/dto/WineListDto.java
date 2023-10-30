package com.your.cellar.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Data
public class WineListDto {


    @JsonProperty("name")
    private String name;

    @JsonProperty("author")
    private String author;

    @JsonProperty("description")
    private String description;

    @JsonProperty("wines")
    private Set<UserWinePublicDto> wines;

}
