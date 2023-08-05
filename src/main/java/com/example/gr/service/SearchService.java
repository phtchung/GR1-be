package com.example.gr.service;

import com.example.gr.entity.Task;
import com.example.gr.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTask(Long user_id) {
        return taskRepository.findTask(user_id);
    }

    public List<Task> getFilteredTasks(String state,LocalDate date, Long user_id){
        return taskRepository.findTaskByFilter(state,date,user_id);
    }

}
