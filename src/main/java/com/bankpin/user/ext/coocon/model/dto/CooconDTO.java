package com.bankpin.user.ext.coocon.model.dto;

import com.bankpin.user.ext.coocon.model.type.ApiType;
import com.bankpin.user.ext.coocon.util.Util;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CooconDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class Common {

        @Builder.Default
        private String fintechIsatk = "3gP97b0RENCIwu3C2Hfl"; // 인증키
        @Builder.Default
        private String fintechIscd = "000036"; // 기관코드
        @Builder.Default
        private String fintechApsno = "001";

        private String apiNm;
        private String apiSvcCd;
        @Builder.Default
        private String fisDscd = "01"; // FIXME 테스트에서 해당값으로만 사용
        @Builder.Default
        private String fisCd = "999"; // FIXME 테스트에서 해당값으로만 사용

        @Builder.Default
        private String tsymd = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        @Builder.Default
        private String trtm = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));

        @Builder.Default // 20자리발송 3자리난수+날짜
        private String isTuno = Util.random3NumToStr() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        private String rpcd;
        private String rsms;
        private String rtnUrl;
        private String asyncYn;
        private String ipInfo;
        private String fintechMarkChnlCd;
        private String fillerC;
        private String lnReqNo;

    }


    @Data
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

    }


}