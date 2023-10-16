package com.your.cellar.book.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Data
public class UserResponseModel {

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("username")
    private String username;

}
