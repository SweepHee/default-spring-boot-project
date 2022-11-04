package com.bankpin.user.ext.coocon.model.dto;

import com.bankpin.user.ext.coocon.model.type.ApiProperties;
import com.bankpin.user.ext.coocon.util.Util;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CooconDTO {
    @Data
    @Builder
    public static class Create
    {
        private String lnReqNo;
        private String comNo;
        private String comDt;
        private String comTm;
        private String comIp;
        private String comCallback;
        private String comAsyncYn;
        private String finGbcd;
        private String finCd;
        private String finDvcCd;
        private String resCd;
        private String resMsg;

        private String comApiCd;

    }

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

        private ApiProperties apiNm;
        private String apiSvcCd;
        @Builder.Default
        private String fisDscd = "01"; // FIXME 쿠콘에서 전달
        @Builder.Default
        private String fisCd = "999"; // FIXME 쿠콘에서 전달

        @Builder.Default
        private String tsymd = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        @Builder.Default
        private String trtm = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));

        @Builder.Default
        private String isTuno = Util.random2NumToStr() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
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
    public static class ApiOutput {

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