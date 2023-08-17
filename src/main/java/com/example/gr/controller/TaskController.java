package com.example.gr.controller;


import com.example.gr.request.CreateTaskRequest;
import com.example.gr.request.CreateUpdateCheckListRequest;
import com.example.gr.request.ShareTaskRequest;
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

    @PutMapping("/update")
    public CommonResponse updateTaskById( @RequestBody CreateTaskRequest createTaskRequest){
        return taskService.updateTaskById(createTaskRequest);
    }

    @PostMapping("/share/{taskId}")
    public CommonResponse shareTaskByPhone(@PathVariable Long taskId, @RequestBody ShareTaskRequest shareTaskRequest){
        return taskService.shareTaskByPhone(taskId,shareTaskRequest);
    }



}
