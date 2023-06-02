package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private final JavaMailSender javaMailSender;


    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void posaljiMejl(String to, String naslov, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("terzicanastasijaa@gmail.com");
        message.setTo(to);
        message.setSubject(naslov);
        message.setText(text);
        this.javaMailSender.send(message);
    }

}
