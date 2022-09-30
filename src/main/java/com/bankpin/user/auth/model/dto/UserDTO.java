package com.bankpin.user.auth.model.dto;

import com.bankpin.user.model.type.HttpCodeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
    }

    @Data
    public static class Detail
    {
        private String custCiNo;
        private String custSimplePwd;
        private String custId;
        private String custNm;
        private String custAuthCd;
    }

    @Data
    public static class Item
    {
        private String custCiNo;
        private String custId;
        private String custNm;
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

}
