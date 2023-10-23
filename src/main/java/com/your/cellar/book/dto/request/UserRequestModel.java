package com.your.cellar.book.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.your.cellar.book.dto.RoleDto;
import com.your.cellar.book.entity.Role;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Data
public class UserRequestModel {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("roles")
    private Set<RoleDto> roles;
}
