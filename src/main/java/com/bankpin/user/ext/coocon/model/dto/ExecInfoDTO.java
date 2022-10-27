package com.bankpin.user.ext.coocon.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ExecInfoDTO {

    @Data
    @Builder
    public static class Create
    {
        private String lnReqNo;
        private String fintecOrgMngno;
        private String lnReqYn;
        private String lnReqDttm;
        private String bankCd;
        private String bankBrchCd;
        private String lnPrdtCd;
        private String lnPrdtNm;
        private String lnAmt;
        private String lstLnLmtAmt;
        private String lnRateKindGbcd;
        private String strdRate;
        private String applyRate;
        private String priRateRsnCntn;
        private String priLmtAmt;
        private String priRate;
        private String lnReqDt;
        private String lnRateCycleCd;
        private String lnAuthDt;
        private String lnCnslYn;
        private String lnCnslReqDttm;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class RequestParams {

        @JsonProperty("COMMON")
        private CooconDTO.Common Common;
        private String loAplcMmNo;
        private String alncIsMnNo;
        private String loPrdCd;
        private String blank1;
        private String fillerI;
        private String rstStCd;
        private String loNAtRsn;
        private String loReqAmt;
        private String ltLoLmAmt;
        private String ltLoRt;
        private String loTeM;
        private String rpayMthdDsCd;
        private String rtTpbsDsCd;
        private String bsRt;
        private String apRt;
        private String prnlYN;
        private String PrnlRsn;
        private String prnlLmAmt;
        private String prnlRt;
        private String aplyDt;
        private String loCtDt;
        private String loExDt;
        private String mRpayFeRt;
        private String loRsprPN;
        private String blank2;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class ResponseParams {

        @JsonProperty("COMMON")
        private CooconDTO.Common Common;
        private String loAplcMmNo;
        private String alncIsMnNo;
        private String loPrdCd;
        private String blank1;
        private String fillerI;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class CancelParams {

        @JsonProperty("COMMON")
        private CooconDTO.Common Common;
        private String loAplcMmNo;
        private String alncIsMnNo;
        private String loPrdCd;
        private String blank1;
        private String fillerI;

    }


}
