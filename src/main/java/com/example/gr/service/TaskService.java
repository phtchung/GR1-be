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

import java.util.ArrayList;
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

    public CommonResponse updateTaskById(CreateTaskRequest createTaskRequest){
        CommonResponse commonResponse = new CommonResponse<>();
        try{
            Task task = taskRepository.findById(createTaskRequest.getTaskId()).orElse(null);

            if (task == null) {
                return commonResponse.result("400","Yêu cầu không hợp lệ!",false);
            }
            if(createTaskRequest.getDateEnd() != null) {
                task.setDateEnd(createTaskRequest.getDateEnd());
            }
            if(createTaskRequest.getDateStart() != null ) {
                task.setDateStart(createTaskRequest.getDateStart());
            }
            if(createTaskRequest.getDescription() != null && !createTaskRequest.getDescription().isEmpty()) {
                task.setDescription(createTaskRequest.getDescription());
            }
            if(createTaskRequest.getState() != null && !createTaskRequest.getState().isEmpty()) {
                task.setState(createTaskRequest.getState());
            }

            taskRepository.save(task);

            return commonResponse.result("200","Update task Thành công!",true);

        }catch (Exception e){
            return commonResponse.result("500", "Có lỗi server!", false);
        }
    }

    public CommonResponse shareTaskByPhone( ShareTaskRequest shareTaskRequest){
            CommonResponse commonResponse = new CommonResponse<>();
            try{
                Task task = taskRepository.findTaskById(shareTaskRequest.getTaskId());
                if (task == null) {
                    return commonResponse.result("400","Yêu cầu không hợp lệ!",false);
                }
                User user = userRepository.findUserByPhone(shareTaskRequest.getPhoneNumber());
                if (user == null){
                    return commonResponse.result("401","Không tìm thấy người dùng hợp lệ!",false);
                }
                if(task.getUser().getUserId() == user.getUserId()){
                    return commonResponse.result("402","Không thể chia sẻ task cho chính mình!",false);

                }
                if(shareTaskRequest.getPermission() != 0 && shareTaskRequest.getPermission() != 1){
                    return commonResponse.result("403","Sai quyền truy cập!",false);

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

    public CommonResponse getShareList(Long taskId){
        CommonResponse commonResponse = new CommonResponse<>();
        try{
            if (taskId == null) {
                return commonResponse.result("400","Yêu cầu không hợp lệ!",false);
            }
            List<User> userSharedList = new ArrayList<>();
            List<Long> shareUserIds = shareTaskRepository.getShareUserByTaskId(taskId);

            for(Long userId : shareUserIds){
                User user = userRepository.findUser(userId);
                userSharedList.add(user);
            }

            return commonResponse.data(userSharedList).result("200","Lấy danh sách shareTask Thành công!",true);

        }catch (Exception e){
            return commonResponse.result("500", "Có lỗi server!", false);
        }
    }

    public CommonResponse deleteSharedUser(Long userId , Long taskId){
        CommonResponse commonResponse = new CommonResponse<>();
        try{

            shareTaskRepository.deleteSharedUser(taskId , userId);
            return commonResponse.result("200","Xóa người dùng thành công!",true);

        }catch (Exception e){
            return commonResponse.result("500", "Có lỗi server!", false);
        }
    }
}
