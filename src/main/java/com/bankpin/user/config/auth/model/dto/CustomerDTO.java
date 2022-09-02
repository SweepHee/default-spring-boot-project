package com.bankpin.user.config.auth.model.dto;

import com.bankpin.user.model.dto.BaseModel;
import lombok.*;

public class CustomerDTO
{
    @Getter
    @Setter
    public static class Create
    {
        private String id;
        private String password;
        private String rePassword;
        private String authority;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Detail extends BaseModel
    {
        private String id;
        private String username;
        private String password;
        private String authority;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Item extends BaseModel
    {
        private String id;
        private String username;
        private String password;
        private String rePassword;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ResponseMessage
    {
        private Boolean error;
        private String message;
    }

}
