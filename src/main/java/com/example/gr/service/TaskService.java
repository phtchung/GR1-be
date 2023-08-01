package com.example.gr.service;

import com.example.gr.entity.Task;
import com.example.gr.repository.TaskRepository;
import com.example.gr.request.CreateTaskRequest;
import com.example.gr.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public CommonResponse getTaskbyId(Long taskId){
            CommonResponse commonResponse = new CommonResponse<>();
            try{
                Optional<Task> task = taskRepository.findById(taskId);

                if (task == null) {
                    return commonResponse.result("400","Yêu cầu không hợp lệ!",false);
                }

                return commonResponse.data(task).result("200","Thành công!",true);

            }catch (Exception e){
                return commonResponse.result("500", "Có lỗi server!", false);
            }
    }

    public CommonResponse updateTaskById(Long taskId, CreateTaskRequest createTaskRequest){
        CommonResponse commonResponse = new CommonResponse<>();
        try{

            Task task = taskRepository.findById(taskId).orElse(null);

            if (task == null) {
                return commonResponse.result("400","Yêu cầu không hợp lệ!",false);
            }
            if(createTaskRequest.getDateEnd() != null) {
                task.setDateEnd(createTaskRequest.getDateEnd());
            }
            if(createTaskRequest.getDateStart() != null) {
                task.setDateStart(createTaskRequest.getDateStart());
            }
            if(createTaskRequest.getDescription() != null) {
                task.setDescription(createTaskRequest.getDescription());
            }
            if(createTaskRequest.getState() != null) {
                task.setState(createTaskRequest.getState());
            }

            taskRepository.save(task);

            return commonResponse.result("200","Update task Thành công!",true);

        }catch (Exception e){
            return commonResponse.result("500", "Có lỗi server!", false);
        }
    }
}
