package com.example.gr.response;

import com.example.gr.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShareListUserResponse {
    List<Task>  tasks;
    private Integer permisson;


}

