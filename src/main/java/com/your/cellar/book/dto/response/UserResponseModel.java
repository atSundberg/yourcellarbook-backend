package com.your.cellar.book.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.your.cellar.book.dto.RoleDto;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
@Data
public class UserResponseModel {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("showWineList")
    private boolean showWineList;

    @JsonProperty("wineListName")
    private String wineListName;

}
