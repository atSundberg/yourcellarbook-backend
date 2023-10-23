package com.your.cellar.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AuthenticationResponse {

        @JsonProperty("token")
        private String token;
        public AuthenticationResponse(String token) {
            this.token = token;
        }
}
