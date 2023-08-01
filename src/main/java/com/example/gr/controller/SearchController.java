package com.example.gr.controller;

import com.example.gr.entity.Task;
import com.example.gr.service.SearchService;
import com.example.gr.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping ("/tasks")
    public List<Task> getAllTask(){
        return searchService.getAllTask();
    }

    @GetMapping("/taskfilter")
    public List<Task> getFilteredTasks(@RequestParam(required = false) String state, @RequestParam(required = false) LocalDate date) {

        return  searchService.getFilteredTasks(state,date);
    }


}
