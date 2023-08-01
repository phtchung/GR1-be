package com.example.gr.controller;

import com.example.gr.entity.Task;
import com.example.gr.response.CommonResponse;
import com.example.gr.service.OverviewService;
import com.example.gr.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskListController {

    private final TaskListService taskListService;

    @Autowired
    public TaskListController(TaskListService taskListService){
        this.taskListService = taskListService;
    }

    @GetMapping(value = "/tasktodo")
    public List<Task> getTaskLastWeekTodo(){
        return taskListService.getTaskLastWeekTodo();
    }

    @GetMapping(value = "/taskinprogress")
    public List<Task> getTaskLastWeekInprogress(){
        return taskListService.getTaskLastWeekInprogress();
    }

    @GetMapping(value = "/taskdone")
    public List<Task> getTaskLastWeekDone(){
        return taskListService.getTaskLastWeekDone();
    }

    @DeleteMapping
    public CommonResponse deleteTask(@RequestParam("id") Long id){
        return taskListService.deleteTask(id);
    }

}
