package com.example.gr.controller;


import com.example.gr.entity.Task;
import com.example.gr.request.CreateTaskRequest;
import com.example.gr.response.CommonResponse;
import com.example.gr.service.OverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OverviewController {

    private final OverviewService overviewService;

    @Autowired
    public OverviewController(OverviewService overviewService){
        this.overviewService = overviewService;
    }

    @GetMapping(value = "/overview/{user_id}")
    public CommonResponse getOverview(@PathVariable Long user_id) {

        return overviewService.getOverview(user_id);
    }

    @PostMapping  ("/create_task")
    public CommonResponse createTask(@RequestBody CreateTaskRequest createTaskRequest){
        return overviewService.createTask(createTaskRequest);

    }





}
