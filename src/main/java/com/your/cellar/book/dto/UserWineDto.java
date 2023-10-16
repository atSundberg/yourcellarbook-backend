package com.your.cellar.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.your.cellar.book.entity.Wine;
import com.your.cellar.book.model.UserWineCompositeKey;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class UserWineDto {

    @JsonProperty("user_wine_id")
    private UserWineCompositeKey id;

    @JsonProperty("storing_location")
    private String storingLocation;

    @JsonProperty("information")
    private String information;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("is_finished")
    private boolean isFinished;

    @JsonProperty("wine")
    private Wine wine;

}
