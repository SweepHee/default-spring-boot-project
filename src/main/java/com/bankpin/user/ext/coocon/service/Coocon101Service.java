package com.bankpin.user.ext.coocon.service;

import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.ext.coocon.config.CooconPropertyConfig;
import com.bankpin.user.ext.coocon.model.dto.*;
import com.bankpin.user.ext.coocon.model.mapper.CooconInqMasMapper;
import com.bankpin.user.ext.coocon.util.Util;
import com.bankpin.user.terms.model.dto.TermsAgreeDTO;
import com.bankpin.user.terms.model.type.TermsType;
import com.bankpin.user.terms.service.TermsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;


import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Coocon101Service {

    private final CooconInqMasMapper cooconInqMasMapper;
    private final TermsService termsService;

    private final CooconPropertyConfig cooconPropertyConfig;


    public CooconDTO.Output request(Coocon101DTO.Request param) throws ParseException, JsonProcessingException {

        JSONObject jsonReq = Util.dtoToJsonObjectPascal(param);

        return WebClient
                .create()
                .post()
                .uri(cooconPropertyConfig.getUri(param.getCommon().getApiNm()))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonReq)
                .retrieve()
                .bodyToMono(CooconDTO.Output.class)
                .block();

    }


    /**
    * 101전문 API 보내기 파라미터 세팅
    * */
    public Coocon101DTO.Request setParameter (
            UserAuth userAuth, CooconCustAuthDtlDTO.Detail authDetail, Coocon101DTO.Request param) {

        if (!StringUtils.hasText(userAuth.getName())
            || !StringUtils.hasText(userAuth.getEmail())
            || !StringUtils.hasText(userAuth.getBirthday())
            || !StringUtils.hasText(authDetail.getCustCphoneNo())
        ) {
            return null;
        }

        String cstmNm = userAuth.getName();
        String email = userAuth.getEmail();
        String rrno = userAuth.getBirthday().replace(".", "").substring(2);

        List<Coocon101DTO.Request.InList1> inlist1Lists = new ArrayList<>();
        List<TermsAgreeDTO.Create> agrees = termsService.findAllByUserId(userAuth.getId());

        for (TermsAgreeDTO.Create agree : agrees) {
            inlist1Lists.add(Coocon101DTO.Request.InList1.termsAgreeToInList1(agree));
        }

        // FIXME 대출신청번호를 우리가 만드는 경우가 아니면 지워야 됨
        String lnReqNo = cooconInqMasMapper.findByMaxLnReqNo();
        param.getInList2().forEach(inlist -> inlist.setLoReqtNo(lnReqNo));

        List<Coocon101DTO.Request.InList3> inlist3Lists = new ArrayList<>();

        return Coocon101DTO.Request.builder()
                .Common(param.getCommon())
                .svcDs("1")
                .cstmNm(cstmNm)
                .rRNo(rrno)

                .cI(param.getCI()) // 금융기관의 정책에 따라 사용
                .brNo(param.getBrNo()) // 사업자등록번호
                .wpDsCd(param.getWpDsCd()) // 사업장관리번호
                .wpNm(param.getWpNm()) // 직장명

                .sCrtcMthd(authDetail.getSelfAuthMethCd()) // 본인인증방법 TBCOM_CUSTAUTH_DTL에서 가져옴
                .sCrtcDtTm(authDetail.getSelfAgreeDttm()) // 본인인증일자시간
                .cstmClPN(authDetail.getCustCphoneNo()) // 고객휴대폰번호
                .cpCrtcTlcm(authDetail.getTeleCd()) // 통신사코드
                .email(email) // 디비에서 가져옴 (TBCOM_CUSTMAS)
                .sCrtcUNo(authDetail.getAuthOrgUnicd()) // 인증기관고유코드 (TBCOM_CUSTAUTH_DTL)
                .listCnt1(inlist1Lists.toArray().length) // TBCOM_CUSTAGREE_LST
                .inList1(inlist1Lists) // TBCOM_CUSTAGREE_LST

                .jonCls(param.getJonCls()) // 직군분류 디비에없음
                .mbrDs(param.getMbrDs()) // 가입자구분 디비에없음
                .bsTp(param.getBsTp()) // 업종 (개인사업자일경우)
                .eplymtTpbs(param.getEplymtTpbs()) // 고용형태 (개인일경우)
                .anIncm(param.getAnIncm()) // 연소득,연매출(개인, 개인사업자만)
                .jnDt(param.getJnDt()) // 입사년월, 개업년월(개인, 개인사업자만)
                .hsOwTpbs(param.getHsOwTpbs()) // 주거소유형태여부(개인,개인사업자만)
                .hsTp(param.getHsTp()) // 주거형태 (개인,개인사업자)
                .loReqAmt(param.getLoReqAmt()) // 대출신청금액
                .loTeM(param.getLoTeM()) // 대출기간개월수
                .rpayMthdDsCd(param.getRpayMthdDsCd()) // 상환방법구분코드
                .rtTpbsDsCd(param.getRtTpbsDsCd()) // 금리종류 구분코드
                .chgCycl(param.getChgCycl()) // 변동주기

                .prsnRehabDs(param.getPrsnRehabDs()) // 개인회생해당여부
                .rehabPymtCmplYN(param.getRehabPymtCmplYN()) // 회생납부완료여부
                .blank2(param.getBlank2()) // 필수에 아무값도 없음

                .listCnt2(param.getInList2().toArray().length)
                .inList2(param.getInList2()) // 반복부2, 대출신청번호(우리가 관리하는 신청번호PK), 대출상품코드. 쿠콘에 물어봐야 함. 은행마다 따로 발송
                .scrpInfoYN(param.getScrpInfoYN()) // 안한다고 했음. N

                // 건보관련 안함 NHISQY, 나머지 다 널
                .nHISQYNCIsNo(param.getNHISQYNCIsNo())
                .nHISQYNCIsYMD(param.getNHISQYNCIsYMD())
                .nHISQYNCCWNm(param.getNHISQYNCCWNm())
                .nHISQYNCCWMDCd(param.getNHISQYNCCWMDCd())
                .nHISQYNCCWQGYMD(param.getNHISQYNCCWQGYMD())
                .nHISQYNCCWQLYMD(param.getNHISQYNCCWQLYMD())
                .blank3(param.getBlank3())
                .nHISPCIqSYM(param.getNHISPCIqSYM())
                .nHISPCIqEYM(param.getNHISPCIqEYM())
                .nHISPCBrNoTY(param.getNHISPCBrNoTY())
                .nHISPCWPNmTY(param.getNHISPCWPNmTY())
                .nHISPCPyrNoTY(param.getNHISPCPyrNoTY())
                .blank4(param.getBlank4())
                .listCnt3(0) // 안받음
                .inList3(inlist3Lists) // 안받음
                .gr1(param.getGr1())
                .sc1(param.getSc1())
                .gr2(param.getGr2())
                .sc2(param.getSc2())
                .carNo(param.getCarNo())
                .fillerI(param.getFillerI())

                .build();

    }

    public void create(UserAuth userAuth, Coocon101DTO.Request param) {

        String lnReqNo = cooconInqMasMapper.findByMaxLnReqNo();

        InqMasDTO.Create build = InqMasDTO.Create.builder()
                .lnReqNo(lnReqNo)
                .lnInqDttm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .lnGbcd("1")
                .lnReqGbcd("1")
                .lnUseGbcd("1")
                .lnReqAmt(param.getLoReqAmt())
                .lnReqTermMm(param.getLoTeM())
                .lnRtnMthCd(param.getRpayMthdDsCd())
                .lnRateKindGbcd(param.getRtTpbsDsCd())
                .lnRateCycleCd(param.getChgCycl())
                .recoverHisYn(param.getPrsnRehabDs())
                .recoverPayCmplYn(param.getRehabPymtCmplYN())
                .custRrno(param.getRRNo()) // 암호화 항목
                .custEmail(param.getEmail())
                .custBusiRegno(param.getBrNo())
                .custBusiplcMngno(param.getWpDsCd())
                .custCmpyNm(param.getWpNm())
                .custYearIncomAmt(param.getAnIncm())
                .custJobClscd(param.getJonCls())
                .custNhisMemGbcd(param.getMbrDs())
                .custBusiUpjongGbcd(param.getBsTp())
                .custEmployGbcd(param.getEplymtTpbs())
                .custEnterYyyymm(param.getJnDt())
                .custHousOwnGbcd(param.getHsOwTpbs())
                .custHousTypecd(param.getHsTp())
                .custCiNo(userAuth.getId())
                //                        .lnHopeDt() // 대출 희망일
                //                        .custCarownYn() // 고객 차량 보유여부 1:자차, 2:렌탈, 3:무소유
                //                        .housOwnCnt() // 주택소유건수
                //                        .marriedYn() // 결혼여부
                //                        .childrenCnt() // 자녀수
                //                        .agoLnAmt() // 기대출금액
                //                        .lnMrtgNo() // 여신담보번호
                .build();

        cooconInqMasMapper.save(build);

    }


    /**
    * 101 요청마다 데이터 생성되므로 사용X
    * */
    public void eachInsertIfNotExists(UserAuth userAuth, Coocon101DTO.Request param) {

        for (Coocon101DTO.Request.InList2 inlist2 : param.getInList2()) {

            InqMasDTO.Create build = InqMasDTO.Create.builder()
                    .lnReqNo(inlist2.getLoReqtNo())
                    .lnInqDttm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .lnGbcd("1")
                    .lnReqGbcd("1")
                    .lnUseGbcd("1")
                    .lnReqAmt(param.getLoReqAmt())
                    .lnReqTermMm(param.getLoTeM())
                    .lnRtnMthCd(param.getRpayMthdDsCd())
                    .lnRateKindGbcd(param.getRtTpbsDsCd())
                    .lnRateCycleCd(param.getChgCycl())
                    .recoverHisYn(param.getPrsnRehabDs())
                    .recoverPayCmplYn(param.getRehabPymtCmplYN())
                    .custRrno(param.getRRNo()) // 암호화 항목
                    .custEmail(param.getEmail())
                    .custBusiRegno(param.getBrNo()) // 사업자일때
                    .custBusiplcMngno(param.getWpDsCd())
                    .custCmpyNm(param.getWpNm())
                    .custYearIncomAmt(param.getAnIncm())
                    .custJobClscd(param.getJonCls())
                    .custNhisMemGbcd(param.getMbrDs())
                    .custBusiUpjongGbcd(param.getBsTp())
                    .custEmployGbcd(param.getEplymtTpbs())
                    .custEnterYyyymm(param.getJnDt())
                    .custHousOwnGbcd(param.getHsOwTpbs())
                    .custHousTypecd(param.getHsTp())
                    .custCiNo(userAuth.getId())
                    //                        .lnHopeDt() // 대출 희망일
                    //                        .custCarownYn() // 고객 차량 보유여부 1:자차, 2:렌탈, 3:무소유
                    //                        .housOwnCnt() // 주택소유건수
                    //                        .marriedYn() // 결혼여부
                    //                        .childrenCnt() // 자녀수
                    //                        .agoLnAmt() // 기대출금액
                    //                        .lnMrtgNo() // 여신담보번호
                    .build();

            if (cooconInqMasMapper.existsByLnReqNo(build.getLnReqNo())) {
                cooconInqMasMapper.update(build);
            } else {
                cooconInqMasMapper.save(build);
            }

        }

    }

}