package com.example.gr.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private Long userId;
    private String phoneNumber;
    private String about;
    private String gender;
    private Date birthday;
    private String profileUrl;
}
