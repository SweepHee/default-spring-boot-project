package com.bankpin.user.code.controller;

import com.bankpin.user.code.model.dto.BankDTO;
import com.bankpin.user.code.service.BankService;
import com.bankpin.user.model.dto.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/code/bank")
public class ApiBankController
{
    @Autowired
    private BankService bankService;

    @GetMapping("/detail")
    public ResponseEntity<ResponseData> detail(@RequestParam("bankCd") String bankCd)
    {
        BankDTO.Detail detail = bankService.selectDetail(
                BankDTO.Param.builder()
                    .bankCd(bankCd)
                    .build());

        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(detail)
                        .build());
    }

}
