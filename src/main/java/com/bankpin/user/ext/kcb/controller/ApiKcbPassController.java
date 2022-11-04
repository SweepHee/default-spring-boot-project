package com.bankpin.user.ext.kcb.controller;

import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.cust.model.dto.CustAuthDtlDTO;
import com.bankpin.user.cust.service.CustAuthDtlService;
import com.bankpin.user.ext.kcb.model.dto.PassAppCertDTO;
import com.bankpin.user.ext.kcb.model.dto.PassAppDTO;
import com.bankpin.user.ext.kcb.service.KcbCertService;
import com.bankpin.user.model.dto.ResponseData;
import com.bankpin.user.model.type.HttpCodeType;
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
@RequestMapping(value = "/api/v1/kcb/pass")
@RequiredArgsConstructor
public class ApiKcbPassController {

    private final KcbCertService kcbCertService;
    private final CustAuthDtlService custAuthDtlService;

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
    public ResponseEntity<ResponseData> send(@RequestBody @Valid PassAppDTO.Param param, HttpServletRequest request, Authentication authentication) throws OkCertException {

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        CustAuthDtlDTO.Detail detail = custAuthDtlService.findByCustCiNoAndSelfAuthMethCd(userAuth.getId(), "2");
        if (detail != null) {

            return ResponseEntity.ok().body(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.BAD_REQUEST.getCode())
                            .message("이미 인증 기록이 있는 회원입니다")
                            .data(detail)
                            .build());

        }

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
    public ResponseEntity<ResponseData> certification(@RequestBody @Valid PassAppCertDTO.Param param, Authentication authentication) throws OkCertException {

        PassAppCertDTO.ReturnData returnData = kcbCertService.certificationPass(param);

        if ("B000".equals(returnData.getRsltCd().getKey())) {

            UserAuth userAuth = (UserAuth) authentication.getPrincipal();
            String lnReqNo = custAuthDtlService.findByMaxLnReqNo();

            CustAuthDtlDTO.Create create = CustAuthDtlDTO.Create.builder()
                    .custCiNo(userAuth.getId())
                    .lnReqNo(lnReqNo)
                    .selfAuthMethCd("2")
                    .selfAgreeDttm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")))
                    .selfAgreeYn("Y")
                    .teleCd(returnData.getTelComCd())
                    .authOrgUnicd(returnData.getTxSeqNo())
                    .custCphoneNo(returnData.getTelNo())
                    .build();

            custAuthDtlService.insertCustAuthDtlDTO(create);

        }

        return ResponseEntity.ok().body(
                ResponseData.builder()
                        .error(!"B000".equals(returnData.getRsltCd().getKey()))
                        .code("B000".equals(returnData.getRsltCd().getKey()) ? HttpCodeType.OK.getCode() : HttpCodeType.BAD_GATEWAY.getCode())
                        .message(returnData.getRsltMsg())
                        .data(returnData)
                        .build());

    }

}