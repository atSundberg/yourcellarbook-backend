package com.your.cellar.book.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.your.cellar.book.entity.Category;
import com.your.cellar.book.entity.Grape;
import com.your.cellar.book.entity.Producer;
import com.your.cellar.book.entity.Region;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
@Data
public class WineResponseModel {

    @JsonProperty("wine_id")
    private Integer wineId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("producer")
    private Producer producer;

    @JsonProperty("vintage")
    private Integer vintage;

    @JsonProperty("region")
    private Region region;

    @JsonProperty("grapes")
    private Set<Grape> grapes = new HashSet<>();

    @JsonProperty("category")
    private Category category;
}
