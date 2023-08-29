package com.example.gr.service;

import com.example.gr.repository.TaskRepository;
import com.example.gr.response.EmailDetails;
import com.example.gr.response.SendEmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TaskRepository taskRepository;

    @Value("${spring.mail.username}")
    private String sender;

    @Scheduled(cron = "0 00 08 * * ?")
    public String sendSimpleMail() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        try {
//
            List<SendEmailRequest> tasksExpire = taskRepository.findTaskExpire(tomorrow);
            System.out.println(tasksExpire.size());
//            tasksExpire.forEach(e -> {
//                System.out.println(e.getEmail());
//            });

            if(tasksExpire.size() == 0){
                return "K có mail";
            }
//            List<SendEmailRequest> sendEmailRequests = new ArrayList<>();
//            SendEmailRequest sendEmail = new SendEmailRequest("thichhocchui2k12019@gmail.com","1");
//            t.add(sendEmail);
            for (SendEmailRequest sendEmailRequest : tasksExpire){
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                System.out.println(sendEmailRequest.getEmail());
                mailMessage.setFrom(sender);
                mailMessage.setTo(sendEmailRequest.getEmail());
                mailMessage.setText("Hi "+sendEmailRequest.getEmail()+"! \n" +
                        "Task "+sendEmailRequest.getTaskName()+" will expire tomorrow. Let's quickly complete and update to the system. \n Wish you work efficiently.\n Thank you ");
                mailMessage.setSubject("About task expiration");

                javaMailSender.send(mailMessage);
            }
            return "succcess";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while Sending Mail1";
        }
    }
}
