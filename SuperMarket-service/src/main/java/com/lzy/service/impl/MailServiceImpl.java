package com.lzy.service.impl;

import com.lzy.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MailServiceImpl  implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String mail, String title, String message) {
        System.out.println("---------------------------------"+from);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 发件人
        mailMessage.setFrom(from);
        // 收件人
        mailMessage.setTo(mail);
        // 邮件名
        mailMessage.setSubject(title);
        // 邮件内容
        mailMessage.setText(message);
        // 发送邮件
        mailSender.send(mailMessage);
    }
}
