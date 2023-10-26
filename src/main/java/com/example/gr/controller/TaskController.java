package com.example.gr.controller;


import com.example.gr.request.CreateTaskRequest;
import com.example.gr.request.ShareTaskRequest;
import com.example.gr.response.CommonResponse;
import com.example.gr.service.JwtService;
import com.example.gr.service.TaskService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    private JwtService jwtService ;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/{taskID}")
    public CommonResponse getTaskById(@PathVariable Long taskID,@RequestHeader("Authorization") String token ){
        token = token.substring(7);

            Claims claims = jwtService.extractAllClaims(token);
            String email = claims.getSubject();

        return taskService.getTaskbyId(taskID,email);
//            return new CommonResponse<>().result("500", "No data!", false);
    }

    @PutMapping("/update")
    public CommonResponse updateTaskById( @RequestBody CreateTaskRequest createTaskRequest){
        return taskService.updateTaskById(createTaskRequest);
    }

    @PostMapping("/share")
    public CommonResponse shareTaskByPhone( @RequestBody ShareTaskRequest shareTaskRequest){
        return taskService.shareTaskByPhone(shareTaskRequest);
    }

//    @GetMapping("/share-with-user")
//    public CommonResponse getShareList(@RequestBody Map<String,Long> body){
//        System.out.println(body.get("userId"));
//        return taskService.getShareList(body.get("userId"), body.get("taskId"));
//    }

    @GetMapping("/share-with-user")
    public CommonResponse getShareList(@RequestParam("userId") Long userId ,@RequestParam("id") Long taskId){
        return taskService.getShareList(userId,taskId);
    }

    @DeleteMapping("/share")
    public CommonResponse deleteSharedUser(@RequestBody Map<String,Long> body){
        return taskService.deleteSharedUser(body.get("userId"), body.get("taskId"));
    }

}
