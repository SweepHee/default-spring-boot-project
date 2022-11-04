package com.bankpin.user.ext.coocon.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

public class InqRsltLstDTO {

    @Data
    @Builder
    public static class Create {

        private String lnReqNo;
        private String fintecOrgMngno;
        @Builder.Default
        private String bankCd = "01"; // FIXME
        @Builder.Default
        private String bankBrchCd = "01"; // FIXME
        private String lnReqGbcd;
        private String lnPrdtCd;
        private String lnPrdtNm;
        private String lnRsltStcd;
        private String nrsltRsnCntn;
        private String lstLnLmtAmt;
        private String lstLnRate;
        private String lstLnTermMm;
        private String lnRtnMthCd;
        private String lnRateKindGbcd;
        private String strdRate;
        private String applyRate;
        private String priRateYn;
        private String priRateRsnCntn;
        private String priLmtAmt;
        private String priRate;
        private String lnValidDttm;
        private String lnRateCycleCd;
        private String lnMidrtnFeeRate;
        private String lnMidrtnFeeYn;
        private String endAllrtnAblYn;
        private String imDepotAblYn;
        private String holiDepotAbleYn;
        private String prinitrEqRtnAblYn;
        private String prinEqRtnAblYn;
        private String minusBbookAblYn;

        // API RESPONSE DTO -> DB DTO 변경
        public static InqRsltLstDTO.Create paramToDto(InqRsltLstDTO.Param param, Param.OUTLIST1 outlist1) {

            return Create.builder()
                    .lnReqNo(outlist1.getLoReqtNo())
                    .fintecOrgMngno(outlist1.getFinEnMnNo())
                    .bankCd(param.getCommon().getFisCd())
                    .bankBrchCd(param.getCommon().getFisDscd())
                    .lnReqGbcd(outlist1.getAplcDs())
                    .lnPrdtCd(outlist1.getLoPrdCd())
                    .lnPrdtNm(outlist1.getLoPrdNm())
                    .lnRsltStcd(outlist1.getRstStCd())
                    .nrsltRsnCntn(outlist1.getLoNAtRsn())
                    .lstLnLmtAmt(outlist1.getLtLoLmAmt())
                    .lstLnRate(outlist1.getLtLoRt())
                    .lstLnTermMm(outlist1.getLoTeM())
                    .lnRtnMthCd(outlist1.getRpayMthdDsCd())
                    .lnRateKindGbcd(outlist1.getRtTpbsDsCd())
                    .strdRate(outlist1.getBsRt())
                    .applyRate(outlist1.getApRt())
                    .priRateYn(outlist1.getPrnlYN())
                    .priRateRsnCntn(outlist1.getPrnlRsn())
                    .priLmtAmt(outlist1.getPrnlLmAmt())
                    .priRate(outlist1.getPrnlRt())
                    .lnValidDttm(outlist1.getLoCdVDtTm())
                    .lnRateCycleCd(outlist1.getChgCycl())
                    .lnMidrtnFeeRate(outlist1.getMRpayFeRt())
                    .lnMidrtnFeeYn(outlist1.getMRpayFeExYN())
                    .endAllrtnAblYn(outlist1.getEDOFRpayAYN())
                    .imDepotAblYn(outlist1.getImDpAYN())
                    .holiDepotAbleYn(outlist1.getHdDpAYN())
                    .prinitrEqRtnAblYn(outlist1.getEqTotPymtYN())
                    .prinEqRtnAblYn(outlist1.getEqPrinPymtYN())
                    .minusBbookAblYn(outlist1.getOdAcYN())
                    .build();

        }

    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class Param {

        @JsonProperty("COMMON")
        private CooconDTO.Common common;
        private String bURL;
        private String blank1;

        @JsonProperty("OUTLIST1")
        private List<OUTLIST1> outList1 = new ArrayList<>();
        private String fillerI;

        @Data
        @Builder
        @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
        public static class OUTLIST1 {

            private String loReqtNo;
            private String finEnMnNo;
            private String aplcDs;
            private String loPrdCd;
            private String loPrdNm;
            private String rstStCd;
            private String loNAtRsn;
            private String ltLoLmAmt;
            private String ltLoRt;
            private String loTeM;
            private String rpayMthdDsCd;
            private String rtTpbsDsCd;
            private String bsRt;
            private String apRt;
            private String prnlYN;
            private String prnlRsn;
            private String prnlLmAmt;
            private String prnlRt;
            private String lwLmRst;
            private String oBRfdAcDs;
            private String inqDt;
            private String loCdVDtTm;
            private String chgCycl;
            private String mRpayFeRt;
            private String mRpayFeExYN;
            private String eDOFRpayAYN;
            private String imDpAYN;
            private String hdDpAYN;
            private String eqTotPymtYN;
            private String eqPrinPymtYN;
            private String odAcYN;
            private String blank2;

        }

    }

}