package com.bankpin.user.ext.coocon.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Coocon106DTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class Param {

        @JsonProperty("COMMON")
        private CooconDTO.Common Common;
        private String loAplcMmNo;
        private String alncIsMnNo;
        private String loPrdCd;
        private String blank1;
        private String fillerI;
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
