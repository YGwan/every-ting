package com.everyTing.member.service.mail;

import com.everyTing.member.service.mail.form.MailForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Async
@Service
public class MailService {

    @Value("${mail.fromAddr}")
    private String fromAddr;

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String userAddr, MailForm form, String fromAddr) {
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userAddr);
        mailMessage.setSubject(form.title());
        mailMessage.setText(form.body());
        mailMessage.setFrom(fromAddr);
        sendMail(mailMessage);
    }

    public void sendMail(String userAddr, MailForm form) {
        sendMail(userAddr, form, fromAddr);
    }

    public void sendMail(SimpleMailMessage mailMessage) {
        mailSender.send(mailMessage);
    }
}
