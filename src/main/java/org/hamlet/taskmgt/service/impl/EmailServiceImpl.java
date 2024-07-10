package org.hamlet.taskmgt.service.impl;

import lombok.RequiredArgsConstructor;
import org.hamlet.taskmgt.payload.request.EmailDetails;
import org.hamlet.taskmgt.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sendEmail;

    @Override
    public void sendEmailAlert(EmailDetails emailDetails) {

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sendEmail);
            simpleMailMessage.setText(emailDetails.getMessageBody());
            simpleMailMessage.setTo(emailDetails.getRecipient());
            simpleMailMessage.setSubject(emailDetails.getSubject());

            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send mail");
        }

    }
}
