package com.example.gr.service;

import com.example.gr.entity.Task;
import com.example.gr.repository.ShareTaskRepository;
import com.example.gr.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ShareTaskRepository shareTaskRepository;

    public List<Task> getAllTask(Long user_id) {
        List<Task> allTask = taskRepository.findTask(user_id);
        List<Task> taskSharedList = shareTaskRepository.getTaskShared(user_id);
        allTask.addAll(taskSharedList);
        return allTask;
    }

    public List<Task> getFilteredTasks(String state,LocalDate date, Long user_id){
        return taskRepository.findTaskByFilter(state,date,user_id);
    }

}
