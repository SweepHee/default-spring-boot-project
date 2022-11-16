package com.bankpin.user.ext.coocon.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Coocon104DTO {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class Param {

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
        private Long logId;

    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class Output {

        @JsonProperty("COMMON")
        private CooconDTO.Common Common;
        private String loAplcMmNo;
        private String alncIsMnNo;
        private String loPrdCd;
        private String blank1;
        private String fillerI;

    }

}
