package com.example.gr.service;

import com.example.gr.entity.Task;
import com.example.gr.repository.TaskRepository;
import com.example.gr.repository.UserRepository;
import com.example.gr.request.CreateTaskRequest;
import com.example.gr.response.CommonResponse;
import com.example.gr.response.OverviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class OverviewService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public CommonResponse getOverview(){
        CommonResponse commonResponse = new CommonResponse<>();
        try{
            LocalDate today = LocalDate.now();
            List<Task> taskToday = taskRepository.findTaskwithEndDateAfterTodayAndNotDone(today);
            long totalTask = taskRepository.findAll().size();
            long totalTaskDone = taskRepository.findTaskwithStateDone().size();
            OverviewResponse overviewResponse = new OverviewResponse(taskToday,totalTask,totalTaskDone);

            if (taskToday == null)
                return commonResponse.result("400","Yêu cầu không hợp lệ!",false);

            return commonResponse.data(overviewResponse).result("200","Thành công!",true);

        }catch (Exception e){
            return commonResponse.result("500","Có lỗi server!",false);
        }
    }


    public CommonResponse createTask(CreateTaskRequest createTaskRequest) {

        CommonResponse commonResponse = new CommonResponse<>();
        try{
            Task task = new Task(
                    userRepository.findUser(createTaskRequest.getUserId()),
                    createTaskRequest.getTaskName(),
                    createTaskRequest.getState(),
                    createTaskRequest.getDescription(),
                    createTaskRequest.getDateStart(),
                    createTaskRequest.getDateEnd(),
                    createTaskRequest.getIsNotify(),
                    createTaskRequest.getIsImportant(),
                    createTaskRequest.getControl()
            );

            Task newTask = taskRepository.save(task);
            if (newTask == null)
                return commonResponse.result("400","Yêu cầu không hợp lệ!",false);

            return commonResponse.result("200","Thành công!",true);
        }catch (Exception e){

            return commonResponse.result("500","Có lỗi server!",false);
        }

    }
}