package com.bankpin.user.ext.mail.service;

import com.bankpin.user.ext.mail.model.dto.MailDTO;
import com.bankpin.user.ext.mail.model.type.MailType;
import com.bankpin.user.model.dto.ResponseData;
import com.bankpin.user.model.type.HttpCodeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;


@Service
@RequiredArgsConstructor
public class MailService {

    public ResponseData send(MailDTO.Parameter parameter, MailType mailType) {

        boolean error = false;
        String message = "";
        int code = HttpCodeType.OK.getCode();
        System.out.println(parameter);

        if (parameter.getSubject() == null || parameter.getContent() == null || parameter.getUsers() == null) {
            return ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.BAD_REQUEST.getCode())
                    .message("제목이나 내용, 대상이 비었습니다")
                    .build();
        }

        Session mailSession = Session.getDefaultInstance(mailType.getProps(), new javax.mail.Authenticator(){
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(mailType.getEmail(), mailType.getPassword());
            }
        });

        try {

            Message mimeMessage = new MimeMessage(mailSession);
            mimeMessage.setFrom(new InternetAddress(mailType.getEmail(), "뱅크핀", "UTF-8"));

            InternetAddress[] receiverList = new InternetAddress[parameter.getUsers().size()];
            for (int i=0; i<parameter.getUsers().size(); i++) {
                receiverList[i] = new InternetAddress(parameter.getUsers().get(i));
            }

            mimeMessage.setRecipients(Message.RecipientType.TO, receiverList);
            mimeMessage.setSubject(parameter.getSubject());
            mimeMessage.setContent(parameter.getContent(), "text/html; charset=UTF-8");
            Transport.send(mimeMessage);

        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            error = true;
            message = e.getMessage();
            code = HttpCodeType.INTERNAL_SERVER_ERROR.getCode();;
        }

        return ResponseData.builder()
                .error(error)
                .code(code)
                .message(message)
                .build();
    }


}