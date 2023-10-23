package com.your.cellar.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RoleDto {

    @JsonProperty("roleId")
    private Long roleId;

    @JsonProperty("roleName")
    private String roleName;
}
