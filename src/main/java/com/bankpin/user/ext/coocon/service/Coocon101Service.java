package com.bankpin.user.ext.coocon.service;

import com.bankpin.user.ext.coocon.model.dto.*;
import com.bankpin.user.ext.coocon.model.mapper.CooconCustAgreeLstMapper;
import com.bankpin.user.ext.coocon.model.mapper.CooconInqMasMapper;
import com.bankpin.user.ext.coocon.model.type.ApiProperties;
import com.bankpin.user.ext.coocon.util.Util;
import com.bankpin.user.sns.auth.model.dto.SnsUserDTO;
import com.bankpin.user.sns.auth.model.mapper.SnsAuthMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Coocon101Service {

    private final SnsAuthMapper snsAuthMapper;
    private final CooconCustAgreeLstMapper cooconCustAgreeLstMapper;
    private final CooconInqMasMapper cooconInqMasMapper;

    public CooconDTO.ApiOutput request(InqMasDTO.RequestParams param) throws ParseException, JsonProcessingException {

        JSONObject jsonReq = Util.dtoToJsonObjectPascal(param);

        return WebClient
                .create()
                .post()
                .uri(ApiProperties.requestURI(ApiProperties.ADL_101_IQ))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonReq)
                .retrieve()
                .bodyToMono(CooconDTO.ApiOutput.class)
                .block();

    }


    /**
    * 101전문 API 보내기 파라미터 세팅
    * */
    public InqMasDTO.RequestParams setParameter (
            SnsUserDTO.Column cust, CooconCustAuthDtlDTO.Detail authDetail, InqMasDTO.RequestParams param) {

        if (StringUtils.isEmptyOrWhitespace(cust.getCustNm())
            || StringUtils.isEmptyOrWhitespace(cust.getCustEmail())
            || StringUtils.isEmptyOrWhitespace(cust.getCustBirth())
            || StringUtils.isEmptyOrWhitespace(authDetail.getCustCphoneNo())
        ) {
            return null;
        }

        String cstmNm = snsAuthMapper.fnDecrypt(cust.getCustNm());
        String custCphoneNo = snsAuthMapper.fnDecrypt(authDetail.getCustCphoneNo());
        String email = snsAuthMapper.fnDecrypt(cust.getCustEmail());
        String rrno = cust.getCustBirth().replace(".", "").substring(2);

        // INLIST1 만들기 (TBCOM_CUSTAGREE_LST)
        List<CooconCustAgreeLstDTO.Create> agreeList = cooconCustAgreeLstMapper.findByCustCiNo(cust.getCustCiNo());
        List<InqMasDTO.RequestParams.InList1> inlist1Lists = new ArrayList<>();
        for (CooconCustAgreeLstDTO.Create agree : agreeList) {
            inlist1Lists.add(InqMasDTO.RequestParams.InList1.agreeLstToInList(agree));
        }

         return InqMasDTO.RequestParams.builder()
                .Common(param.getCommon())
                .svcDs("1")
                .cstmNm(cstmNm)
                .rRNo(rrno)

//                .cI() // 금융기관의 정책에 따라 사용
//                .brNo() // 사업자등록번호
//                .wpDsCd() // 사업장관리번호
//                .wpNm() // 직장명

                .sCrtcMthd(authDetail.getSelfAuthMethCd()) // 본인인증방법 TBCOM_CUSTAUTH_DTL에서 가져옴
                .sCrtcDtTm(authDetail.getSelfAgreeDttm()) // 본인인증일자시간
                .cstmClPN(custCphoneNo) // 고객휴대폰번호
                .cpCrtcTlcm(authDetail.getTeleCd()) // 통신사코드
                .email(email) // 디비에서 가져옴 (TBCOM_CUSTMAS)
                .sCrtcUNo(authDetail.getAuthOrgUnicd()) // 인증기관고유코드 (TBCOM_CUSTAUTH_DTL)
                .listCnt1(agreeList.size()) // TBCOM_CUSTAGREE_LST
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

                .listCnt2(param.getInList2().size())
                .inList2(param.getInList2()) // 반복부2, 대출신청번호(우리가 관리하는 신청번호PK), 대출상품코드. 쿠콘에 물어봐야 함. 은행마다 따로 발송
                .scrpInfoYN("N") // 안한다고 했음.
                // 건보관련 안함 NHISQY...

                .build();

    }


    public void eachInsertIfNotExists(SnsUserDTO.Column cust, InqMasDTO.RequestParams param) {

        for (InqMasDTO.RequestParams.InList2 inlist2 : param.getInList2()) {

            InqMasDTO.Column build = InqMasDTO.Column.builder()
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
                    .custCiNo(cust.getCustCiNo())
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