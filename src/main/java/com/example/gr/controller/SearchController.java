package com.example.gr.controller;

import com.example.gr.entity.Task;
import com.example.gr.service.SearchService;
import com.example.gr.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping ("/tasks/{user_id}")
    public List<Task> getAllTask(@PathVariable Long user_id){
        return searchService.getAllTask(user_id);
    }

    @GetMapping("/taskfilter/{user_id}")
    public List<Task> getFilteredTasks(@RequestParam(required = false) String state, @RequestParam(required = false) LocalDate date, @PathVariable Long user_id) {

        return  searchService.getFilteredTasks(state,date,user_id);
    }


}
