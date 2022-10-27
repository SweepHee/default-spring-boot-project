package com.bankpin.user.sns.auth.model.dto;

import com.bankpin.user.model.type.HttpCodeType;
import com.bankpin.user.sns.auth.model.type.SnsType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Base64Utils;

public class SnsUserDTO
{
    @Data
    @Builder
    @AllArgsConstructor
    public static class Create
    {
        private String id;
        private String password;
        private String rePassword;
        private String name;
        private String custAuthCd;
        private Boolean custActvGbcd;
        private String custEmail;
        private String custBirth;
        private String custGender;

    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Detail {

        private String custCiNo;
        private SnsType custSnsType;

    }

    @Data
    public static class Column
    {
        private String custCiNo;
        private String custSimplePwd;
        private String custId;
        private String custNm;
        private String custAuthCd;
        private String custActvGbcd;
        private String custEmail;
        private String custBirth;
        private String custGender;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class ResponseMessage
    {
        @Builder.Default
        private Boolean error = false;
        @Builder.Default
        private Integer code = HttpCodeType.OK.getCode();
        private String message;
        private Object data;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Payload
    {
        private String aud;
        private String sub;
        private long authTime;
        private String iss;
        private String nickname;
        private long exp;
        private String iat;
        private String email;
        private String birthday;
        private String gender;

        public static Payload base64decodeToPayload (String encodedPayload) throws JsonProcessingException {
            String payload = new String(Base64Utils.decodeFromUrlSafeString(encodedPayload));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(payload, Payload.class);
        }
    }




}
