package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void posaljiMejl(String to, String naslov, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bogovac.in20.2021@uns.ac.rs");
        message.setTo(to);
        message.setSubject(naslov);
        message.setText(text);
        javaMailSender.send(message);
    }

}
