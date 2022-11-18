package com.bankpin.user.ext.kcb.controller;


import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.cust.model.dto.CustAuthDtlDTO;
import com.bankpin.user.cust.service.CustAuthDtlService;
import com.bankpin.user.ext.coocon.model.dto.CooconLogDTO;
import com.bankpin.user.ext.kcb.model.dto.KcbSmsLogDTO;
import com.bankpin.user.ext.kcb.model.dto.SmsCertDTO;
import com.bankpin.user.ext.kcb.model.dto.SmsDTO;
import com.bankpin.user.ext.kcb.service.KcbCertService;
import com.bankpin.user.ext.kcb.service.KcbSmsLogService;
import com.bankpin.user.model.dto.ResponseData;
import com.bankpin.user.model.type.HttpCodeType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kcb.module.v3.exception.OkCertException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@RestController
@RequestMapping(value = "/api/v1/kcb/sms")
@RequiredArgsConstructor
public class ApiKcbSmsController {

    private final KcbCertService kcbCertService;
    private final CustAuthDtlService custAuthDtlService;
    private final KcbSmsLogService kcbSmsLogService;
    private final ObjectMapper objectMapper;


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
    public ResponseEntity<ResponseData> send(@RequestBody @Valid SmsDTO.Param param, HttpServletRequest request, Authentication authentication)
            throws OkCertException, JsonProcessingException {

        KcbSmsLogDTO.Create logDto = KcbSmsLogDTO.Create.builder()
                .id(param.getLogId())
                .build();
        System.out.println("===========logDto"+logDto);

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        CustAuthDtlDTO.Detail detail = custAuthDtlService.findByCustCiNoAndSelfAuthMethCd(userAuth.getId(), "2");
        if (detail != null) {

            ResponseData responseData = ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.BAD_REQUEST.getCode())
                    .message("이미 인증 기록이 있는 회원입니다")
                    .data(detail)
                    .build();
            this.logUpdate(logDto, responseData);
            return ResponseEntity.ok().body(responseData);

        }

        SmsDTO.ReturnData returnData = kcbCertService.sendSms(param, request);
        this.logUpdate(logDto, returnData);

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
    public ResponseEntity<ResponseData> certification(@RequestBody @Valid SmsCertDTO.Param param, Authentication authentication)
            throws OkCertException, JsonProcessingException {

        KcbSmsLogDTO.Create logDto = KcbSmsLogDTO.Create.builder()
                .id(param.getLogId())
                .build();

        SmsCertDTO.ReturnData returnData = kcbCertService.certificationSMS(param);
        this.logUpdate(logDto, returnData);

        if ("B000".equals(returnData.getRsltCd().getKey())) {

            UserAuth userAuth = (UserAuth) authentication.getPrincipal();
            String lnReqNo = custAuthDtlService.findByMaxLnReqNo();

            CustAuthDtlDTO.Create create = CustAuthDtlDTO.Create.builder()
                    .custCiNo(userAuth.getId())
                    .lnReqNo(lnReqNo)
                    .selfAuthMethCd("2")
                    .selfAgreeDttm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")))
                    .selfAgreeYn("Y")
                    .teleCd(returnData.getTelComCd())
                    .authOrgUnicd(returnData.getTxSeqNo())
                    .custCphoneNo(returnData.getTelNo())
                    .build();

            int save = custAuthDtlService.insertCustAuthDtlDTO(create);

        }


        return ResponseEntity.ok().body(
                ResponseData.builder()
                        .error(!"B000".equals(returnData.getRsltCd().getKey()))
                        .code("B000".equals(returnData.getRsltCd().getKey()) ? HttpCodeType.OK.getCode() : HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                        .message(returnData.getRsltMsg())
                        .data(returnData)
                        .build());

    }

    private <T> void logUpdate(KcbSmsLogDTO.Create logDTO, T t) throws JsonProcessingException {
        logDTO.setApiOutCntn(objectMapper.writeValueAsString(t));
        kcbSmsLogService.update(logDTO);
    }

}