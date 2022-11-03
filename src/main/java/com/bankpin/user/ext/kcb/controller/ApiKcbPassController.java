package com.bankpin.user.ext.kcb.controller;

import com.bankpin.user.ext.kcb.model.dto.PassAppCertDTO;
import com.bankpin.user.ext.kcb.model.dto.PassAppDTO;
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
@RequestMapping(value = "/api/v1/kcb/pass")
@RequiredArgsConstructor
public class ApiKcbPassController {

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

    @PostMapping("/send")
    public ResponseEntity<ResponseData> send(@RequestBody @Valid PassAppDTO.Param param, HttpServletRequest request) throws OkCertException {

        PassAppDTO.ReturnData returnData = kcbCertService.sendPass(param, request);

        return ResponseEntity.ok().body(
                ResponseData.builder()
                        .error(!"B000".equals(returnData.getRsltCd().getKey()))
                        .code("B000".equals(returnData.getRsltCd().getKey()) ? HttpCodeType.OK.getCode() : HttpCodeType.BAD_GATEWAY.getCode())
                        .message(returnData.getRsltMsg())
                        .data(returnData)
                        .build());

    }


    @PostMapping("/cert")
    public ResponseEntity<ResponseData> certification(@RequestBody @Valid PassAppCertDTO.Param param) throws OkCertException {

        PassAppCertDTO.ReturnData returnData = kcbCertService.certificationPass(param);

        return ResponseEntity.ok().body(
                ResponseData.builder()
                        .error(!"B000".equals(returnData.getRsltCd().getKey()))
                        .code("B000".equals(returnData.getRsltCd().getKey()) ? HttpCodeType.OK.getCode() : HttpCodeType.BAD_GATEWAY.getCode())
                        .message(returnData.getRsltMsg())
                        .data(returnData)
                        .build());

    }

}