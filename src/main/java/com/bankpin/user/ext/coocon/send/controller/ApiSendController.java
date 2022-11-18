package com.bankpin.user.ext.coocon.send.controller;

import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.ext.coocon.model.dto.*;
import com.bankpin.user.ext.coocon.service.CooconInqRsltLstService;
import com.bankpin.user.ext.coocon.model.type.ApiType;
import com.bankpin.user.ext.coocon.service.*;
import com.bankpin.user.model.dto.ResponseData;
import com.bankpin.user.model.type.HttpCodeType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import kcb.org.json.JSONObject;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Slf4j
@RestController
@RequestMapping(value = "/api/v1/coocon")
@RequiredArgsConstructor
public class ApiSendController {

    private final Coocon101Service coocon101Service;
    private final Coocon103Service coocon103Service;
    private final Coocon105Service coocon105Service;
    private final Coocon106Service coocon106Service;
    private final CooconLogService cooconLogService;
    private final CooconCustAuthService cooconCustAuthService;
    private final CooconInqRsltLstService cooconInqRsltLstService;
    private final ObjectMapper objectMapper;

    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ResponseData> handleMismatchedInputException(MismatchedInputException exception) {
        log.error("MismatchedInputException: {}", exception.getMessage());
        return ResponseEntity.ok().body(
                ResponseData.builder()
                        .error(true)
                        .code(HttpCodeType.BAD_REQUEST.getCode())
                        .message("JSON형태가 잘못되었거나 동의항목코드가 잘못되었습니다.")
                        .build());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseData> handleNullPointerException(NullPointerException exception) {
        log.error("NullPointerException: {}", exception.getMessage());
        return ResponseEntity.ok().body(
                ResponseData.builder()
                        .error(true)
                        .code(HttpCodeType.BAD_REQUEST.getCode())
                        .message("JSON형태가 잘못되었거나 동의항목코드가 잘못되었습니다.")
                        .build());
    }




    /**
    * 대출금리한도 조회
    * */
    @PostMapping("/101")
    public ResponseEntity<ResponseData> request101
        (@RequestBody Coocon101DTO.Request param, Authentication authentication) throws JsonProcessingException {

        ApiType apiNm = ApiType.ADL_101_IQ;
        CooconLogDTO.Create logDto = CooconLogDTO.Create.builder()
                .id(param.getLogId())
                .build();

        CooconDTO.Common common = CooconDTO.Common.builder()
                .apiNm(apiNm.toString())
                .apiSvcCd(apiNm.getServiceCode())
                .rtnUrl("http://localhost:8507/user/coocon/callback/102") // Todo 특정 기관에서만 사용된다고 되어있음 물어보기
                .build();

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        CooconCustAuthDtlDTO.Detail authDetail = cooconCustAuthService.findAuthDtlByCustCiNo(userAuth.getId());

        if (authDetail == null) {

            ResponseData responseData = ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .message("유저 상세 정보 없음")
                    .build();
            this.logUpdate(logDto, responseData);
            return ResponseEntity.ok(responseData);

        }

        param.setCommon(common);
        Coocon101DTO.Request apiParam = coocon101Service.setParameter(userAuth, authDetail, param);
        JSONObject a = new JSONObject(apiParam);
        System.out.println(a);

        if (apiParam == null) {
            ResponseData responseData = ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .message("유저 정보 부족")
                    .build();
            this.logUpdate(logDto, responseData);
            return ResponseEntity.ok(responseData);
        }

        CooconDTO.Output body;

        try {

            body = coocon101Service.request(param);
            coocon101Service.create(userAuth, apiParam);
            this.logUpdate(logDto, body);
            cooconLogService.update(logDto);

        } catch (Exception e) {

            ResponseData responseData = ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .message(e.getMessage())
                    .build();
            this.logUpdate(logDto, responseData);
            return ResponseEntity.ok(responseData);

        }


        return ResponseEntity.ok(
                ResponseData.builder()
                        .error(false)
                        .code(HttpCodeType.OK.getCode())
                        .message("")
                        .data(body)
                        .build());
    }

    /**
     * 대출신청접수
     * */
    @PostMapping("/103")
    public ResponseEntity<ResponseData> request103(@RequestBody Coocon103DTO.Param param, Authentication authentication)
            throws JsonProcessingException {

        ApiType apiNm = ApiType.ADL_103_IQ;
        CooconLogDTO.Create logDto = CooconLogDTO.Create.builder()
                .id(param.getLogId())
                .build();

        CooconDTO.Common common = CooconDTO.Common.builder()
                .apiNm("ADL_103_IQ")
                .apiSvcCd(apiNm.getServiceCode())
                .rtnUrl("http://localhost:8507/user/coocon/callback/104") // Todo 특정 기관에서만 사용된다고 되어있음 물어보기
                .build();

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        CooconCustAuthDtlDTO.Detail authDetail = cooconCustAuthService.findAuthDtlByCustCiNo(userAuth.getId());

        if (authDetail == null) {
            ResponseData responseData = ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .message("유저 상세 정보 없음")
                    .build();
            this.logUpdate(logDto, responseData);
            return ResponseEntity.ok(responseData);
        }

        InqRsltLstDTO.Create result = cooconInqRsltLstService
                .findByLnReqNoAndFinEnMnNoAndLoPrdCd(param.getLoReqtNo(), param.getFinEnMnNo(), param.getLoPrdCd());

        if (result == null) {
            ResponseData responseData = ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .message("대출조회 및 결과내역 없음")
                    .build();
            this.logUpdate(logDto, responseData);
            return ResponseEntity.ok(responseData);
        }

        param.setCommon(common);
        param.setFinEnMnNo(result.getFintecOrgMngno());
        param.setLoPrdCd(result.getLnPrdtCd());
        param.setCstmCipn(authDetail.getCustCphoneNo()); // Todo 전화번호, 이메일, 이름 암호화필요
        param.setCstmEml(userAuth.getEmail());
        param.setCstmNm(userAuth.getName());

        Coocon103DTO.Output body;

        try {

            body = coocon103Service.request(param);
            this.logUpdate(logDto, body);

            JSONObject test = new JSONObject(body);
            System.out.println(test);

            if (Objects.equals(body.getCommon().getRpcd(), "00000")) {
                coocon103Service.upsert(body);
            } else {
                throw new Exception(body.getCommon().getRsms() +" :: "+  body.getCommon().getRpcd());
            }

        } catch (Exception e) {

            ResponseData responseData = ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .message(e.getMessage())
                    .build();
            this.logUpdate(logDto, responseData);
            return ResponseEntity.ok(responseData);

        }

        return ResponseEntity.ok(
                ResponseData.builder()
                        .error(false)
                        .code(HttpCodeType.OK.getCode())
                        .message("")
                        .data(body)
                        .build());
    }



    /**
    * 대출실행결과통보(대출실행결과확인) ADL_105_IQ
     * 104가 안 올 경우(혹은 확인해보고 싶은 경우) 호출하는 API. 굳이 저장할 필요 없어 보임 (104로 들어왔을때 저장시키기)
    * */
    @PostMapping(value = "/105", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData> request105(@RequestBody Coocon105DTO.Param param, Authentication authentication) throws JsonProcessingException {

        ApiType apiNm = ApiType.ADL_105_IQ;
        CooconLogDTO.Create logDto = CooconLogDTO.Create.builder()
                .id(param.getLogId())
                .build();

        CooconDTO.Common common = CooconDTO.Common.builder()
                .apiNm(apiNm.toString())
                .apiSvcCd(apiNm.getServiceCode())
                .rtnUrl("") // Todo 특정기관만 사용한다고 함 물어보기
                .build();

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        CooconCustAuthDtlDTO.Detail authDetail = cooconCustAuthService.findAuthDtlByCustCiNo(userAuth.getId());

        if (authDetail == null) {
            ResponseData responseData = ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .message("유저 상세 정보 없음")
                    .build();
            this.logUpdate(logDto, responseData);
            return ResponseEntity.ok(responseData);
        }

        InqRsltLstDTO.Create result = cooconInqRsltLstService
                .findByLnReqNoAndFinEnMnNoAndLoPrdCd(param.getLoAplcMmNo(), param.getAlncIsMnNo(), param.getLoPrdCd());

        if (result == null) {
            ResponseData responseData = ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .message("대출조회 및 결과내역 없음")
                    .build();
            this.logUpdate(logDto, responseData);
            return ResponseEntity.ok(responseData);
        }


        param.setCommon(common);
        param.setAlncIsMnNo(result.getFintecOrgMngno());
        param.setLoPrdCd(result.getLnPrdtCd());

        Coocon105DTO.Output body;

        try {

            body = coocon105Service.request(param);
            this.logUpdate(logDto, body);
            JSONObject test = new JSONObject(body);
            System.out.println(test);
            if (Objects.equals(body.getCommon().getRpcd(), "00000")) {
                coocon105Service.upsert(body);
            } else {
                throw new Exception(body.getCommon().getRsms() +" :: "+  body.getCommon().getRpcd());
            }


        } catch (Exception e) {
            ResponseData responseData = ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .message(e.getMessage())
                    .build();
            this.logUpdate(logDto, responseData);
            return ResponseEntity.ok(responseData);
        }

        return ResponseEntity.ok(
                ResponseData.builder()
                        .error(false)
                        .code(HttpCodeType.OK.getCode())
                        .message("")
                        .data(body)
                        .build());
    }

    /**
     * 대출취소
     * */
    @PostMapping("/106")
    public ResponseEntity<ResponseData> request106
        (@RequestBody Coocon106DTO.Param param, Authentication authentication) throws JsonProcessingException {

        ApiType apiNm = ApiType.ADL_106_IF;
        CooconLogDTO.Create logDto = CooconLogDTO.Create.builder()
                .id(param.getLogId())
                .build();

        CooconDTO.Common common = CooconDTO.Common.builder()
                .apiNm(apiNm.toString())
                .apiSvcCd(apiNm.getServiceCode())
                .rtnUrl("")
                .build();

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        CooconCustAuthDtlDTO.Detail authDetail = cooconCustAuthService.findAuthDtlByCustCiNo(userAuth.getId());

        if (authDetail == null) {
            ResponseData responseData = ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .message("유저 상세 정보 없음")
                    .build();
            this.logUpdate(logDto, responseData);
            return ResponseEntity.ok(responseData);
        }

        InqRsltLstDTO.Create result = cooconInqRsltLstService
                .findByLnReqNoAndFinEnMnNoAndLoPrdCd(param.getLoAplcMmNo(), param.getAlncIsMnNo(), param.getLoPrdCd());

        if (result == null) {
            ResponseData responseData = ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .message("대출조회 및 결과내역 없음")
                    .build();
            this.logUpdate(logDto, responseData);
            return ResponseEntity.ok(responseData);
        }
        param.setCommon(common);
        param.setAlncIsMnNo(result.getFintecOrgMngno());
        param.setLoPrdCd(result.getLnPrdtCd());
        Coocon106DTO.Output body;

        if (coocon106Service.isCancel(param)) {
            ResponseData responseData = ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.ALREADY_REPORTED.getCode())
                    .message("이미 취소했습니다")
                    .build();
            this.logUpdate(logDto, responseData);
            return ResponseEntity.ok(responseData);
        }

        try {

            body = coocon106Service.request(param);
            this.logUpdate(logDto, body);
            JSONObject test = new JSONObject(body);
            System.out.println(test);
            if (Objects.equals(body.getCommon().getRpcd(), "00000")) {
                coocon106Service.cancel(body);
            } else {
                throw new Exception(body.getCommon().getRsms() +" :: "+  body.getCommon().getRpcd());
            }

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


    private <T> void logUpdate(CooconLogDTO.Create logDTO, T t) throws JsonProcessingException {
        logDTO.setApiOutCntn(objectMapper.writeValueAsString(t));
        cooconLogService.update(logDTO);
    }


}
