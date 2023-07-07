package com.example.springboot.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MailService {

    private JavaMailSender javaMailSender;

    public void send(String email) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        mimeMessage.setFrom("tanyakorsun0208@gmail.com");
        helper.setTo(email);
        helper.setText("Hello mazefaka <a href='https://img.freepik.com/free-photo/sushi-set-hot-rolls-avocado-california-salmon-rolls_141793-1279.jpg'>ЖМИ СЮДИ</a>",true);

        javaMailSender.send(mimeMessage);
    }
}
