package com.everyTing.member.service.mail;

import com.everyTing.core.exception.TingServerException;
import com.everyTing.member.dto.form.MailForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.everyTing.member.errorCode.MemberServerErrorCode.MSER_005;

@Service
public class MailService {

    @Value("${mail.fromAddr}")
    private String fromAddr;

    @Value("${mail.retry.count}")
    private int retryCount;

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendMail(String userAddr, MailForm form) {
        sendMail(userAddr, form, fromAddr);
    }

    public void sendMail(String userAddr, MailForm form, String fromAddr) {
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userAddr);
        mailMessage.setSubject(form.title());
        mailMessage.setText(form.body());
        mailMessage.setFrom(fromAddr);

        sendMail(retryCount, mailMessage);
    }

    public void sendMail(int retryCount, SimpleMailMessage mailMessage) {
        if (retryCount < 0) {
            throw new TingServerException(MSER_005);
        }

        try {
            mailSender.send(mailMessage);
        } catch (Exception e) {
            sendMail(retryCount - 1, mailMessage);
        }
    }
}
