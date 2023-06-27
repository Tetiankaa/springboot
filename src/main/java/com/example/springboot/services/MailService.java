package com.example.springboot.services;

import com.example.springboot.models.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender javaMailSender;

  public void sendMail(User user) {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

      try {
          helper.setTo("katyakorsun2006@gmail.com ");
          helper.setText("<h2>user "+user.toString()+" created</h2>",true);
//          helper.setText("<h2>HELLO SMERDIUCHKA</h2>",true);
//          helper.setFrom(new InternetAddress("tanyakorsun0208@gmail.com"));
      } catch (MessagingException e) {
          throw new RuntimeException(e);
      }

      javaMailSender.send(mimeMessage);
  }

}
