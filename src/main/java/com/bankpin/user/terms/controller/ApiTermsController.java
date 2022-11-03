package com.bankpin.user.terms.controller;


import com.bankpin.user.model.dto.ResponseData;
import com.bankpin.user.model.type.HttpCodeType;
import com.bankpin.user.sns.auth.model.dto.SnsUserDTO;
import com.bankpin.user.sns.auth.service.UserSnsAuthService;
import com.bankpin.user.terms.model.dto.TermsAgreeDTO;
import com.bankpin.user.terms.service.TermsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping(value = "/api/v1/terms")
@RequiredArgsConstructor
public class ApiTermsController {

    private final UserSnsAuthService userSnsAuthService;
    private final TermsService termsService;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseData> handleHttpMessageNotReadableExceptionException(HttpMessageNotReadableException exception) {
        log.error("HttpMessageNotReadableException: {}", exception.getMessage());
        return ResponseEntity.ok().body(
                ResponseData.builder()
                        .error(true)
                        .code(HttpCodeType.BAD_REQUEST.getCode())
                        .message("JSON형태가 잘못되었거나 동의항목코드가 잘못되었습니다.")
                        .build());
    }

    /**
    * 약관동의
    * */
    @PostMapping("/agree")
    public ResponseEntity<ResponseData> agree(@RequestBody TermsAgreeDTO.Param parameter, Authentication authentication) {

        SnsUserDTO.Column user = userSnsAuthService.findByCustCiNo(authentication.getName());
        parameter.setUserId(user.getCustCiNo());

        termsService.upsertEach(parameter);

        return ResponseEntity.ok().body(
                ResponseData.builder()
                        .error(false)
                        .code(HttpCodeType.OK.getCode())
                        .message("")
                        .build());
        
    }


    @PostMapping("/remove")
    public ResponseEntity<ResponseData> remove(@RequestBody TermsAgreeDTO.Param parameter, Authentication authentication) {

        SnsUserDTO.Column user = userSnsAuthService.findByCustCiNo(authentication.getName());
        parameter.setUserId(user.getCustCiNo());

        termsService.deleteEach(parameter);

        return ResponseEntity.ok().body(
                ResponseData.builder()
                        .error(false)
                        .code(HttpCodeType.OK.getCode())
                        .message("")
                        .build());

    }

}