package com.bankpin.user.ext.mail.controller;

import com.bankpin.user.ext.mail.model.dto.MailDTO;
import com.bankpin.user.ext.mail.model.type.MailType;
import com.bankpin.user.ext.mail.service.MailService;
import com.bankpin.user.model.dto.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/mail/hiworks")
@RequiredArgsConstructor
public class MailHiworksController {


    private final MailService mailService;

    /**
    * 하이웍스 메일보내기
    * */
    @GetMapping("/send")
    public ResponseEntity<ResponseData> send(MailDTO.Parameter parameter) {
        return ResponseEntity.ok(mailService.send(parameter, MailType.HIWORKS));
    }

}