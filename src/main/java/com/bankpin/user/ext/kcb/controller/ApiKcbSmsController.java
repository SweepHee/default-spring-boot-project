package com.bankpin.user.ext.kcb.controller;


import com.bankpin.user.ext.kcb.model.dto.SmsCertDTO;
import com.bankpin.user.ext.kcb.model.dto.SmsDTO;
import com.bankpin.user.ext.kcb.service.KcbCertService;
import com.bankpin.user.model.dto.ResponseData;
import com.bankpin.user.model.type.HttpCodeType;
import kcb.module.v3.exception.OkCertException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping(value = "/api/v1/kcb/sms")
@RequiredArgsConstructor
public class ApiKcbSmsController {

    private final KcbCertService kcbCertService;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseData> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("MethodArgumentNotValidException: {}", exception.getMessage());
        return ResponseEntity.ok().body(
                ResponseData.builder()
                        .error(true)
                        .code(HttpCodeType.BAD_REQUEST.getCode())
                        .message("잘못된 요청값이 있습니다." + exception.getMessage())
                        .build());
    }
    
    /**
    * SMS 발송
    * */
    @PostMapping("/send")
    public ResponseEntity<ResponseData> send(@RequestBody @Valid SmsDTO.Param param, HttpServletRequest request) throws OkCertException {

        SmsDTO.ReturnData returnData = kcbCertService.sendSms(param, request);

        return ResponseEntity.ok().body(
                ResponseData.builder()
                            .error(!"B000".equals(returnData.getRsltCd().getKey()))
                            .code("B000".equals(returnData.getRsltCd().getKey()) ? HttpCodeType.OK.getCode() : HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message(returnData.getRsltMsg())
                            .data(returnData)
                            .build());

    }

    
    /**
    * SMS 검증
    * */
    @PostMapping("/cert")
    public ResponseEntity<ResponseData> certification(@RequestBody @Valid SmsCertDTO.Param param) throws OkCertException {

        SmsCertDTO.ReturnData returnData = kcbCertService.certificationSMS(param);

        return ResponseEntity.ok().body(
                ResponseData.builder()
                        .error(!"B000".equals(returnData.getRsltCd().getKey()))
                        .code("B000".equals(returnData.getRsltCd().getKey()) ? HttpCodeType.OK.getCode() : HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                        .message(returnData.getRsltMsg())
                        .data(returnData)
                        .build());

    }

}