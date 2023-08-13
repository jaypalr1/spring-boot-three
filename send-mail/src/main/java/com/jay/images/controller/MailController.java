package com.jay.images.controller;

import com.jay.images.dto.EmailBody;
import com.jay.images.service.EmailService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

  private final EmailService emailService;

  @PostMapping("/send")
  public ResponseEntity<String> sendMail(@RequestBody @Valid EmailBody emailBody) {
    emailService.sendMail(emailBody.getTo(), emailBody.getSubject(), emailBody.getBody());

    return new ResponseEntity<>("Mail Sent Successfully.", HttpStatus.OK);
  }
}
