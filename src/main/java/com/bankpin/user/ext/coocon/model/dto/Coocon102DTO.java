package com.bankpin.user.ext.coocon.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class Coocon102DTO {

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
        private Long logId;

        @JsonProperty("OUTLIST1")
        @Valid
        private List<InqRsltLstDTO.Param.OUTLIST1> outList1 = new ArrayList<>();
        private String fillerI;

        @Data
        @Builder
        @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
        public static class OUTLIST1 {


            @Length(max=14)
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

    @Data
    @Builder
    public static class Output {

        @JsonProperty("COMMON")
        private CooconDTO.Common Common;
        private String fillerI;

    }

}
