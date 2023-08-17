package com.example.gr.controller;

import com.example.gr.request.CreateTaskRequest;
import com.example.gr.request.CreateUpdateCheckListRequest;
import com.example.gr.request.ShareTaskRequest;
import com.example.gr.response.CommonResponse;
import com.example.gr.service.CheckListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/task")
public class CheckListController {
    private final CheckListService checkListService ;

    @Autowired
    public CheckListController(CheckListService checkListService ){
        this.checkListService = checkListService;
    }

    @PostMapping("/checklist")
    public CommonResponse createCheckList(@RequestBody CreateUpdateCheckListRequest createUpdateCheckListRequest){
        return checkListService.createCheckList(createUpdateCheckListRequest);
    }

    @GetMapping("/checklist/{taskId}")
    public CommonResponse getCheckList(@PathVariable Long taskId ){
        return checkListService.getCheckList(taskId);
    }

    @PutMapping("/checklist/update")
    public CommonResponse updateCheckListById( @RequestBody CreateUpdateCheckListRequest createUpdateCheckListRequest){
        return checkListService.updateCheckListById(createUpdateCheckListRequest);
    }

    @DeleteMapping("/checklist")
    public CommonResponse deleteCheckList(@RequestBody Map<String,Long> body){
        return checkListService.deleteCheckList(body.get("checkListId"));
    }
}
