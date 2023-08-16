package com.example.gr.service;

import com.example.gr.entity.ShareTask;
import com.example.gr.entity.Task;
import com.example.gr.entity.User;
import com.example.gr.repository.ShareTaskRepository;
import com.example.gr.repository.TaskRepository;
import com.example.gr.repository.UserRepository;
import com.example.gr.request.CreateTaskRequest;
import com.example.gr.request.ShareTaskRequest;
import com.example.gr.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShareTaskRepository shareTaskRepository;

    public CommonResponse getTaskbyId(Long taskId){
            CommonResponse commonResponse = new CommonResponse<>();
            try{
                Task task = taskRepository.findTaskById(taskId);
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

    public CommonResponse shareTaskByPhone(Long taskId , ShareTaskRequest shareTaskRequest){
            CommonResponse commonResponse = new CommonResponse<>();
            try{
                Task task = taskRepository.findById(taskId).orElse(null);
                if (task == null) {
                    return commonResponse.result("400","Yêu cầu không hợp lệ!",false);
                }
                User user = userRepository.findUserByPhone(shareTaskRequest.getPhoneNumber());
                if (user == null){
                    return commonResponse.result("400","Không tìm thấy người dùng hợp lệ!",false);
                }
                if(task.getUser().getUserId() == user.getUserId()){
                    return commonResponse.result("400","Không thể chia sẻ task cho chính mình!",false);

                }
                if(shareTaskRequest.getPermission() != 0 && shareTaskRequest.getPermission() != 1){
                    return commonResponse.result("400","Sai quyền truy cập!",false);

                }
                ShareTask shareTask = new ShareTask(
                        task,
                        user.getUserId(),
                        shareTaskRequest.getPermission()

                );
                shareTaskRepository.save(shareTask);

                return commonResponse.result("200","Share task mới Thành công!",true);

            }catch (Exception e){
                return commonResponse.result("500", "Có lỗi server!", false);
            }
    }
}
