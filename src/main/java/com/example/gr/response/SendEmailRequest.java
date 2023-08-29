package com.example.gr.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SendEmailRequest {
    private String email;
    private String taskName;


}
