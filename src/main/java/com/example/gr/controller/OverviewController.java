package com.example.gr.controller;


import com.example.gr.entity.Task;
import com.example.gr.request.CreateTaskRequest;
import com.example.gr.response.CommonResponse;
import com.example.gr.service.OverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OverviewController {

    private final OverviewService overviewService;
    CreateTaskRequest task;

    @Autowired
    public OverviewController(OverviewService overviewService){
        this.overviewService = overviewService;
    }

    @GetMapping(value = "/overview")
    public CommonResponse getOverview() {

        return overviewService.getOverview();
    }

    @PostMapping  ("/create_task")
    public CommonResponse createTask(@RequestBody CreateTaskRequest createTaskRequest){
        return overviewService.createTask(createTaskRequest);

    }





}
