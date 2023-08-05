package com.example.gr.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateCheckListRequest {
    private String title;
    private Integer progress;
    private Long taskId;
    private Date dateEnd;
    private String note ;
}
