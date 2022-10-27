package com.bankpin.user.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

public class UserDTO
{
    @Data
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
    public static class Detail
    {
        private String custCiNo;
        private String custSimplePwd;
        private String custId;
        private String custNm;
        private String custAuthCd;
        private String custEmail;
        private String custBirth;
        private String custGender;
    }

    @Data
    public static class Item
    {
        private String custCiNo;
        private String custId;
        private String custNm;
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
        private Integer code = HttpStatus.OK.value();
        private String message;
        private Object data;
    }

}
