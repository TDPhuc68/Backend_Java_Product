package com.Project.product.controller;

import com.Project.product.entity.Mail;
import org.slf4j.Logger;
import com.Project.product.service.EmailService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    static final Logger log =
            LoggerFactory.getLogger(EmailController.class);

    @Autowired
    EmailService emailService;
    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody Mail mail)  {
        log.info("send Email " + mail);
        emailService.sendEmail(mail.getTo(), mail.getSubject(), mail.getBody());
    }

    @PostMapping("/sendAttachment")
    public void sendEmailWithAttachment(@RequestBody Mail mail) throws MessagingException, FileNotFoundException {
        log.info("send Attachment mail: " + mail);
        emailService.sendEmailWithAttachment(mail.getTo(), mail.getSubject(), mail.getBody(), mail.getAttachment());
    }

    @PostMapping("/sendTemplate")
    public void sendTemplateMail(@RequestBody Mail mail) throws MessagingException {
        log.info("send  Template mail: " + mail);
        emailService.sendTemplateMail(mail);
    }
}
