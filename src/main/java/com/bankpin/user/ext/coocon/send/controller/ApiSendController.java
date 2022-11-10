package com.bankpin.user.ext.coocon.send.controller;

import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.ext.coocon.model.dto.*;
import com.bankpin.user.ext.coocon.service.CooconInqRsltLstService;
import com.bankpin.user.ext.coocon.model.type.ApiType;
import com.bankpin.user.ext.coocon.service.*;
import com.bankpin.user.model.dto.ResponseData;
import com.bankpin.user.model.type.HttpCodeType;
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


    private final CooconCustAuthService cooconCustAuthService;

    private final CooconInqRsltLstService cooconInqRsltLstService;


    /**
    * 대출금리한도 조회
    * */
    @PostMapping("/101")
    public ResponseEntity<ResponseData> request101
        (@RequestBody Coocon101DTO.Request param, Authentication authentication) {

        ApiType apiNm = ApiType.ADL_101_IQ;

        CooconDTO.Common common = CooconDTO.Common.builder()
                .apiNm(apiNm.toString())
                .apiSvcCd(apiNm.getServiceCode())
                .rtnUrl("http://localhost:8507/user/coocon/callback/102") // Todo 특정 기관에서만 사용된다고 되어있음 물어보기
                .build();

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        CooconCustAuthDtlDTO.Detail authDetail = cooconCustAuthService.findAuthDtlByCustCiNo(userAuth.getId());

        if (authDetail == null) {
            return ResponseEntity.ok(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message("유저 상세 정보 없음")
                            .build());
        }

        param.setCommon(common);
        Coocon101DTO.Request apiParam = coocon101Service.setParameter(userAuth, authDetail, param);
        JSONObject a = new JSONObject(apiParam);
        System.out.println(a);

        if (apiParam == null) {

            return ResponseEntity.ok(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message("유저 정보 부족")
                            .build());
        }

        CooconDTO.Output body;

        try {

            body = coocon101Service.request(param);
            coocon101Service.create(userAuth, apiParam);

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
                        .data(body)
                        .build());
    }

    /**
     * 대출신청접수
     * */
    @PostMapping("/103")
    public ResponseEntity<ResponseData> request103(@RequestBody ReqLstDTO.RequestParams param, Authentication authentication) {

        // 대출신청번호가 파라미터로 넘어올거라 예상

        ApiType apiNm = ApiType.ADL_103_IQ;
        System.out.println("......");

        CooconDTO.Common common = CooconDTO.Common.builder()
                .apiNm("ADL_103_IQ")
                .apiSvcCd(apiNm.getServiceCode())
                .rtnUrl("http://localhost:8507/user/coocon/callback/104") // Todo 특정 기관에서만 사용된다고 되어있음 물어보기
                .build();

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        CooconCustAuthDtlDTO.Detail authDetail = cooconCustAuthService.findAuthDtlByCustCiNo(userAuth.getId());

        if (authDetail == null) {
            return ResponseEntity.ok(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message("유저 상세 정보 없음")
                            .build());
        }

        InqRsltLstDTO.Create result = cooconInqRsltLstService
                .findByLnReqNoAndFinEnMnNoAndLoPrdCd(param.getLoReqtNo(), param.getFinEnMnNo(), param.getLoPrdCd());

        if (result == null) {
            return ResponseEntity.ok(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message("대출조회 및 결과내역 없음")
                            .build());
        }

        param.setCommon(common);
        param.setFinEnMnNo(result.getFintecOrgMngno());
        param.setLoPrdCd(result.getLnPrdtCd());
        param.setCstmCipn(authDetail.getCustCphoneNo()); // Todo 전화번호, 이메일, 이름 암호화필요
        param.setCstmEml(userAuth.getEmail());
        param.setCstmNm(userAuth.getName());

        CooconDTO.Output body;

        try {

            body = coocon103Service.request(param);
            JSONObject test = new JSONObject(body);
            System.out.println(test);

            if (Objects.equals(body.getCommon().getRpcd(), "00000")) {
                coocon103Service.upsert(body);
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
                        .data(body)
                        .build());
    }



    /**
    * 대출실행결과통보(대출실행결과확인) ADL_105_IQ
     * 104가 안 올 경우(혹은 확인해보고 싶은 경우) 호출하는 API. 굳이 저장할 필요 없어 보임 (104로 들어왔을때 저장시키기)
    * */
    @PostMapping(value = "/105", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData> request105(@RequestBody ExecInfoDTO.ResponseParams param, Authentication authentication) {

        ApiType apiNm = ApiType.ADL_105_IQ;

        CooconDTO.Common common = CooconDTO.Common.builder()
                .apiNm(apiNm.toString())
                .apiSvcCd(apiNm.getServiceCode())
                .rtnUrl("") // Todo 특정기관만 사용한다고 함 물어보기
                .build();

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        CooconCustAuthDtlDTO.Detail authDetail = cooconCustAuthService.findAuthDtlByCustCiNo(userAuth.getId());

        if (authDetail == null) {
            return ResponseEntity.ok(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message("유저 상세 정보 없음")
                            .build());
        }

        InqRsltLstDTO.Create result = cooconInqRsltLstService
                .findByLnReqNoAndFinEnMnNoAndLoPrdCd(param.getLoAplcMmNo(), param.getAlncIsMnNo(), param.getLoPrdCd());

        if (result == null) {
            return ResponseEntity.ok(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message("대출조회 및 결과내역 없음")
                            .build());
        }


        param.setCommon(common);
        param.setAlncIsMnNo(result.getFintecOrgMngno());
        param.setLoPrdCd(result.getLnPrdtCd());

        ExecInfoDTO.RequestParams requestParams;

        try {

            requestParams = coocon105Service.request(param);
            JSONObject test = new JSONObject(requestParams);
            System.out.println(test);
            if (Objects.equals(requestParams.getCommon().getRpcd(), "00000")) {
                coocon105Service.upsert(requestParams);
            } else {
                throw new Exception(requestParams.getCommon().getRsms() +" :: "+  requestParams.getCommon().getRpcd());
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
                        .data(requestParams)
                        .build());
    }

    /**
     * 대출취소
     * */
    @PostMapping("/106")
    public ResponseEntity<ResponseData> request106
        (@RequestBody ExecInfoDTO.CancelParams param, Authentication authentication) {

        ApiType apiNm = ApiType.ADL_106_IF;

        CooconDTO.Common common = CooconDTO.Common.builder()
                .apiNm(apiNm.toString())
                .apiSvcCd(apiNm.getServiceCode())
                .rtnUrl("")
                .build();

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        CooconCustAuthDtlDTO.Detail authDetail = cooconCustAuthService.findAuthDtlByCustCiNo(userAuth.getId());

        if (authDetail == null) {
            return ResponseEntity.ok(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message("유저 상세 정보 없음")
                            .build());
        }

        InqRsltLstDTO.Create result = cooconInqRsltLstService
                .findByLnReqNoAndFinEnMnNoAndLoPrdCd(param.getLoAplcMmNo(), param.getAlncIsMnNo(), param.getLoPrdCd());

        if (result == null) {
            return ResponseEntity.ok(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message("대출조회 및 결과내역 없음")
                            .build());
        }
        param.setCommon(common);
        param.setAlncIsMnNo(result.getFintecOrgMngno());
        param.setLoPrdCd(result.getLnPrdtCd());
        ExecInfoDTO.CancelParams requestParams;

        if (coocon106Service.isCancel(param)) {

            return ResponseEntity.ok(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.ALREADY_REPORTED.getCode())
                            .message("이미 취소했습니다")
                            .build());
        }

        try {

            requestParams = coocon106Service.request(param);
            JSONObject test = new JSONObject(requestParams);
            System.out.println(test);
            if (Objects.equals(requestParams.getCommon().getRpcd(), "00000")) {
                coocon106Service.cancel(requestParams);
            } else {
                throw new Exception(requestParams.getCommon().getRsms() +" :: "+  requestParams.getCommon().getRpcd());
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



    private static class Validator {

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Property {

            public CooconCustAuthDtlDTO.Detail authDetail;
            public InqRsltLstDTO.Create result;
            public ResponseData responseData;

        }

    }


    private Validator.Property basicValidator(UserAuth userAuth) {

        CooconCustAuthDtlDTO.Detail authDetail = cooconCustAuthService.findAuthDtlByCustCiNo(userAuth.getId());

        if (authDetail == null) {
            return Validator.Property.builder()
                    .responseData(ResponseData.builder()
                                    .error(true)
                                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                                    .message("유저 상세 정보 없음")
                                    .build())
                    .build();
        }

        InqRsltLstDTO.Create result = cooconInqRsltLstService.findByLnReqNo(authDetail.getLnReqNo());

        if (result == null) {
            return Validator.Property.builder()
                    .responseData(ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message("대출조회 및 결과내역 없음")
                            .build())
                    .build();
        }

        return Validator.Property.builder()
                .authDetail(authDetail)
                .result(result)
                .build();
    }

}
