package com.example.gr.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthenticationResponse {
    private String token;
    private String message;
    private String status;
    private Long user_id;

}
