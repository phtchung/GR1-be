package com.example.gr.service;

import com.example.gr.entity.Task;
import com.example.gr.repository.ShareTaskRepository;
import com.example.gr.repository.TaskRepository;
import com.example.gr.repository.UserRepository;
import com.example.gr.request.CreateTaskRequest;
import com.example.gr.response.CommonResponse;
import com.example.gr.response.OverviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OverviewService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShareTaskRepository shareTaskRepository;

    public CommonResponse getOverview(Long user_id){
        CommonResponse commonResponse = new CommonResponse<>();
        try{
            LocalDate today = LocalDate.now();
            List<Task> taskToday = taskRepository.findTaskwithEndDateAfterTodayAndNotDone(today , user_id);
            long totalTask = taskRepository.findTask(user_id).size();
            long totalTaskDone = taskRepository.findTaskwithStateDone(user_id).size();
            long totalTaskImportant = taskRepository.findTaskwithStateImportant(user_id).size();
            List<Task> taskShared = getTaskShared(user_id);
            for(Task task : taskShared){
                if(task.getDateEnd().toLocalDate().isAfter(LocalDate.now()) && !task.getState().equals("Done")){
                    taskToday.add(task);
                    totalTask = totalTask + 1 ;
                    if(task.getIsImportant()){
                        totalTaskImportant = totalTaskImportant + 1;
                    }
                }
            }

            OverviewResponse overviewResponse = new OverviewResponse(taskToday,totalTask,totalTaskDone,totalTaskImportant);

            if (taskToday == null)
                return commonResponse.result("400","Yêu cầu không hợp lệ!",false);

            return commonResponse.data(overviewResponse);

        }catch (Exception e){
            return commonResponse.result("500","Có lỗi server!",false);
        }
    }

    public List<Task> getTaskShared(Long userId){
        List<Task> taskSharedList = shareTaskRepository.getTaskShared(userId);
        return taskSharedList;
    }

    public CommonResponse createTask( CreateTaskRequest createTaskRequest) {

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


            if (task == null)
                return commonResponse.result("400","Yêu cầu không hợp lệ!",false);
            taskRepository.save(task);
            return commonResponse.data(task).result("200"," Thêm Thành công!",true);
        }catch (Exception e){

            return commonResponse.result("500","Có lỗi server!",false);
        }

    }
}
