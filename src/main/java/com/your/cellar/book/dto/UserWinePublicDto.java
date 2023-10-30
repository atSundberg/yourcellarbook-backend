package com.your.cellar.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.your.cellar.book.entity.Wine;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserWinePublicDto {

    @JsonProperty("information")
    private String information;

    @JsonProperty("rating")
    private Integer rating;

    @JsonProperty("thoughts")
    private String thoughts;

    @JsonProperty("wine")
    private Wine wine;

}


