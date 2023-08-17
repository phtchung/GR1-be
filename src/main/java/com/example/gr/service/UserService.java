package com.example.gr.service;

import com.example.gr.entity.User;
import com.example.gr.repository.UserRepository;
import com.example.gr.request.UpdateUserRequest;
import com.example.gr.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository ;


    public CommonResponse updateUser( UpdateUserRequest updateUserRequest) {

        CommonResponse commonResponse = new CommonResponse<>();
        try {
            User user = userRepository.findUser(updateUserRequest.getUserId());

            if (user == null) {
                return commonResponse.result("400","Yêu cầu không hợp lệ!",false);
            }
            if (updateUserRequest.getGender() != null && !updateUserRequest.getGender().isEmpty()) {
                user.setGender(updateUserRequest.getGender());
            }
            if (updateUserRequest.getAbout() != null && !updateUserRequest.getAbout().isEmpty()) {
                user.setAbout(updateUserRequest.getAbout());
            }
            if (updateUserRequest.getBirthday() != null ) {
                user.setBirthday(updateUserRequest.getBirthday());
            }
            if (updateUserRequest.getPhoneNumber() != null && !updateUserRequest.getPhoneNumber().isEmpty()) {
                user.setPhoneNumber(updateUserRequest.getPhoneNumber());
            }

            // Save the updated user object
            userRepository.save(user);

            return commonResponse.result("200","Thành công!",true);
        } catch (Exception e) {
            return commonResponse.result("500", "Có lỗi server!", false);
        }
    }

    public CommonResponse getUser(Long userId){
        CommonResponse commonResponse = new CommonResponse<>();
        try{
            User user = userRepository.findUser(userId);
            if (user == null) {
                return commonResponse.result("400","Yêu cầu không hợp lệ!",false);
            }
            return commonResponse.data(user).result("200","Thành công!",true);


        }catch (Exception e){
            return commonResponse.result("500", "Có lỗi server!", false);

        }
    }


}
