package com.jay.images.service;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

//  private final JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private String userName;

  @Value("${spring.mail.password}")
  private String password;

  @Value("${spring.mail.host}")
  private String smtpServer;

  public void sendMail(String to, String subject, String message) {
    Properties props = new Properties();

     /* Specifies the IP address of your default mail server
          for e.g if you are using gmail server as an email sever
           you will pass smtp.gmail.com as value of mail.smtp host.
           As shown here in the code. Change accordingly, if your email id is not a gmail id
     */
    props.put("mail.smtp.host", "smtp.gmail.com");

    //below mentioned mail.smtp.port is optional
    props.put("mail.smtp.port", smtpServer);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    /* Pass Properties object(props) and Authenticator object for authentication to Session instance */

    Session session = Session.getInstance(props, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
      }
    });

    try {

//      Create an instance of MimeMessage, it accepts MIME types and headers
      MimeMessage mimeMessage = new MimeMessage(session);
      mimeMessage.setFrom(new InternetAddress(userName));
      mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      mimeMessage.setSubject(subject);
      mimeMessage.setText(message);

//      Transport class is used to deliver the message to the recipients
      Transport.send(mimeMessage);

    } catch (Exception e) {
      e.printStackTrace();
    }

//    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//
//    simpleMailMessage.setFrom(userName); //This can be removed, and we can use Principal (Security) to fetch username
//    simpleMailMessage.setTo(to);
//    simpleMailMessage.setSubject(subject);
//    simpleMailMessage.setText(text);
//
//    javaMailSender.send(simpleMailMessage);
  }
}
