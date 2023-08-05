package com.example.gr.controller;

import com.example.gr.entity.Task;
import com.example.gr.response.CommonResponse;
import com.example.gr.service.OverviewService;
import com.example.gr.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskListController {

    private final TaskListService taskListService;

    @Autowired
    public TaskListController(TaskListService taskListService){
        this.taskListService = taskListService;
    }

    @GetMapping(value = "/todo/{user_id}")
    public List<Task> getTaskLastWeekTodo(@PathVariable Long user_id){
        return taskListService.getTaskLastWeekTodo(user_id);
    }

    @GetMapping(value = "/inprogress/{user_id}")
    public List<Task> getTaskLastWeekInprogress(@PathVariable Long user_id){
        return taskListService.getTaskLastWeekInprogress(user_id);
    }

    @GetMapping(value = "/done/{user_id}")
    public List<Task> getTaskLastWeekDone(@PathVariable Long user_id){
        return taskListService.getTaskLastWeekDone(user_id);
    }

    @DeleteMapping
    public CommonResponse deleteTask(@RequestParam("id") Long id){
        return taskListService.deleteTask(id);
    }

}
