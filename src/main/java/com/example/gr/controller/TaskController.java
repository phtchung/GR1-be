package com.example.gr.controller;


import com.example.gr.request.CreateTaskRequest;
import com.example.gr.request.CreateUpdateCheckListRequest;
import com.example.gr.request.ShareTaskRequest;
import com.example.gr.response.CommonResponse;
import com.example.gr.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("/share")
    public CommonResponse shareTaskByPhone( @RequestBody ShareTaskRequest shareTaskRequest){
        return taskService.shareTaskByPhone(shareTaskRequest);
    }

    @GetMapping("/share-with-user/{taskId}")
    public CommonResponse getShareList( @PathVariable Long taskId){
        return taskService.getShareList(taskId);
    }

    @DeleteMapping("/share")
    public CommonResponse deleteSharedUser(@RequestBody Map<String,Long> body){
        return taskService.deleteSharedUser(body.get("userId"), body.get("taskId"));
    }

}
