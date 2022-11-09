package com.bankpin.user.ext.coocon.receive.controller;

import com.bankpin.user.ext.coocon.model.dto.ExecInfoDTO;
import com.bankpin.user.ext.coocon.model.dto.InqRsltLstDTO;
import com.bankpin.user.ext.coocon.service.Coocon102Service;
import com.bankpin.user.ext.coocon.service.Coocon104Service;
import com.bankpin.user.model.dto.ResponseData;
import com.bankpin.user.model.type.HttpCodeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/coocon/callback")
@RequiredArgsConstructor
public class ApiReceiveController {

    private final Coocon102Service coocon102Service;
    private final Coocon104Service coocon104Service;


    /**
     * 대출금리한도 조회
     * */
    @PostMapping("/102")
    public ResponseEntity<ResponseData> response102(@RequestBody InqRsltLstDTO.Param param) {

        try {

            coocon102Service.eachInsertIfNotExists(param);

        } catch (Exception e) {

            return ResponseEntity.ok(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message(e.getMessage())
                            .build());

        }

        return ResponseEntity.ok(
                ResponseData.builder()
                        .error(false)
                        .code(HttpCodeType.OK.getCode())
                        .message("")
                        .data(param)
                        .build());
    }


    /**
    * 대출실행결과통지 ADL_104_IF
    * */
    @PostMapping("/104")
    public ResponseEntity<ResponseData> response104(@RequestBody ExecInfoDTO.RequestParams param) {

        try {

            coocon104Service.upsert(param);

        } catch (Exception e) {

            return ResponseEntity.ok(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message(e.getMessage())
                            .build());

        }

        return ResponseEntity.ok(
                ResponseData.builder()
                        .error(false)
                        .code(HttpCodeType.OK.getCode())
                        .message("")
                        .build());

    }

}





