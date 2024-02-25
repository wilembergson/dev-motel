package com.example.backend.service.impl;

import com.example.backend.model.dto.EmailDTO;
import com.example.backend.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(EmailDTO emailDTO){
        var message = new SimpleMailMessage();
        message.setFrom("noreply@email.com");
        message.setTo(emailDTO.to());
        message.setSubject(emailDTO.subject());
        message.setText(emailDTO.body());
        mailSender.send(message);
    }

    public void sendCreateAccountEmail(String email){
        var message = new SimpleMailMessage();
        message.setFrom("noreply@email.com");
        message.setTo(email);
        message.setSubject("Conta DevMotel criada.");
        message.setText("Sua conta no site da DevMotel for criada com sucesso.");
        mailSender.send(message);
    }
}
