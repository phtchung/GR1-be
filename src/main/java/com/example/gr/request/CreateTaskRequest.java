package com.example.gr.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateTaskRequest {
    private String taskName;
    private String state;
    private String description;
    private Date dateStart;
    private Date dateEnd;
    private Boolean isNotify;
    private Boolean isImportant;
    private Integer control;
    private Long userId;
    private Long taskId;

}
