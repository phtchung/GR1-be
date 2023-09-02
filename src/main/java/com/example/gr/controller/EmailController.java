package com.example.gr.controller;



import com.example.gr.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class EmailController {
    @Autowired
    private EmailService emailService;


//    @GetMapping("/sendMail")
//    public String sendMail()
//    {
//        String status
//                = emailService.sendSimpleMail();
//
//        return status;
//    }
}
