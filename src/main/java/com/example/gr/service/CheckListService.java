package com.example.gr.service;

import com.example.gr.entity.CheckList;
import com.example.gr.entity.Task;
import com.example.gr.repository.CheckListRepository;
import com.example.gr.repository.TaskRepository;
import com.example.gr.request.CreateTaskRequest;
import com.example.gr.request.CreateUpdateCheckListRequest;
import com.example.gr.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CheckListService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CheckListRepository checkListRepository;


    public CommonResponse createCheckList(CreateUpdateCheckListRequest createUpdateCheckListRequest){
        CommonResponse commonResponse = new CommonResponse<>();
        try{
            Task task = taskRepository.findById(createUpdateCheckListRequest.getTaskId()).orElse(null);
            if (task == null) {
                return commonResponse.result("400","Yêu cầu không hợp lệ!",false);
            }
            Date dateEnd = (createUpdateCheckListRequest.getDateEnd() != null && createUpdateCheckListRequest.getDateEnd().compareTo(task.getDateEnd()) <= 0) ? createUpdateCheckListRequest.getDateEnd() : task.getDateEnd();
            CheckList newCheckList = new CheckList(
                    createUpdateCheckListRequest.getTitle(),
                    dateEnd,
                    createUpdateCheckListRequest.getProgress(),
                    createUpdateCheckListRequest.getNote(),
                    task
            );
            checkListRepository.save(newCheckList);
            return commonResponse.result("200","Tạo checklist mới Thành công!",true);

        }catch (Exception e){
            return commonResponse.result("500", "Có lỗi server!", false);
        }
    }

    public CommonResponse getCheckList(Long taskId ){
        CommonResponse commonResponse = new CommonResponse<>();
        try{
            if (taskId == null) {
                return commonResponse.result("400","Yêu cầu không hợp lệ!",false);
            }

            List<CheckList> checkLists = checkListRepository.findAllCheckList(taskId);

            return commonResponse.data(checkLists).result("200","Lấy danh sách Thành công!",true);

        }catch (Exception e){
            return commonResponse.result("500", "Có lỗi server!", false);
        }
    }

    public CommonResponse deleteCheckList( Long id){
        CommonResponse commonResponse = new CommonResponse<>();
        try{
            checkListRepository.deleteById(id);
            return commonResponse.result("200","Thành công!",true);

        }catch (Exception e){
            return commonResponse.result("500","Có lỗi server!",false);

        }

    }


    public CommonResponse updateCheckListById(Long checkListId, CreateUpdateCheckListRequest createUpdateCheckListRequest){
        CommonResponse commonResponse = new CommonResponse<>();
        try{
            CheckList checkList = checkListRepository.findById(checkListId).orElse(null);

            if (checkList == null) {
                return commonResponse.result("400","Yêu cầu không hợp lệ!",false);
            }

            if(createUpdateCheckListRequest.getDateEnd() != null && createUpdateCheckListRequest.getDateEnd().compareTo(checkList.getDateEnd()) <= 0) {
                checkList.setDateEnd(createUpdateCheckListRequest.getDateEnd());
            }
            checkList.setNote(createUpdateCheckListRequest.getNote());
            if(createUpdateCheckListRequest.getTitle() != null) {
                checkList.setTitle(createUpdateCheckListRequest.getTitle());
            }
            if(createUpdateCheckListRequest.getProgress() != null) {
                checkList.setProgress(createUpdateCheckListRequest.getProgress());
            }

            checkListRepository.save(checkList);

            return commonResponse.result("200","Update checklist Thành công!",true);

        }catch (Exception e){
            return commonResponse.result("500", "Có lỗi server!", false);
        }
    }
}
