package com.bankpin.user.ext.coocon.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Coocon103DTO {

    @Data
    @Builder
    @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class Param {

        @JsonProperty("COMMON")
        private CooconDTO.Common Common;
        private String loReqtNo;
        private String finEnMnNo;
        private String loPrdCd;
        private String cstmCipn;
        private String cstmEml;
        private String cstmNm;
        private String blank1;
        private String fillerI;
        private Long logId;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class Output {

        @JsonProperty("COMMON")
        private CooconDTO.Common Common;
        private String loReqtNo;
        private String finEnMnNo;
        private String loPrdCd;
        private String bRpgUrl;
        private String cbTelNo;
        private String blank1;
        private String fillerI;

    }

}
