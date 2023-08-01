package com.example.gr.service;

import com.example.gr.entity.Task;
import com.example.gr.repository.TaskRepository;
import com.example.gr.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskListService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTaskLastWeekTodo() {
        LocalDate today = LocalDate.now();
        LocalDate resultDate = today.minusDays(7);
        return taskRepository.findTasksWithin7DaysTodo(resultDate);
    }

    public List<Task> getTaskLastWeekInprogress() {
        LocalDate today = LocalDate.now();
        LocalDate resultDate = today.minusDays(7);
        return taskRepository.findTasksWithin7DaysInprogress(resultDate);

    }

    public List<Task> getTaskLastWeekDone() {
        LocalDate today = LocalDate.now();
        LocalDate resultDate = today.minusDays(7);
        return taskRepository.findTasksWithin7DaysDone(resultDate);
    }

    public CommonResponse deleteTask( Long id){
        CommonResponse commonResponse = new CommonResponse<>();
        try{
            taskRepository.deleteById(id);
            return commonResponse.result("200","Thành công!",true);

        }catch (Exception e){
            return commonResponse.result("500","Có lỗi server!",false);

        }

    }




}
