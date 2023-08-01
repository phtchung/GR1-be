package com.example.gr.controller;


import com.example.gr.request.CreateTaskRequest;
import com.example.gr.response.CommonResponse;
import com.example.gr.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/{taskID}")
    public CommonResponse getTaskById(@PathVariable Long taskID){
        return taskService.getTaskbyId(taskID);
    }

    @PutMapping("/{taskID}")
    public CommonResponse updateTaskById(@PathVariable Long taskID, @RequestBody CreateTaskRequest createTaskRequest){
        return taskService.updateTaskById(taskID,createTaskRequest);
    }

}
