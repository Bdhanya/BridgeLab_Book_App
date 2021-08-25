package com.BookApplication.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;


@Component
public class EmailUtil {
    private JavaMailSender javaMailSender;
    private static final Logger logger= LoggerFactory.getLogger(EmailUtil.class);
    @Autowired
    public EmailUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String toAddress, String emailSubject,String emailBody) {
        try {
            logger.info("Inside method sendEmail()");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(toAddress);
            mimeMessageHelper.setSubject(emailSubject);
            mimeMessageHelper.setText(emailBody);
            javaMailSender.send(mimeMessage);
            logger.info("Returning From method sendEmail()");
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
