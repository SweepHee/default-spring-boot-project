package com.bankpin.user.code.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

public class BankDTO
{
    @Data
    public static class Detail
    {
        private String bankCd;
        private String bankNm;
        private String bankBrchCd;
        private String bankAddr;
    }

    @Data
    public static class Item
    {
        private String bankCd;
        private String bankNm;
        private String bankBrchCd;
        private String bankAddr;
    }

    @Data
    public static class Create
    {
        private String bankCd;
        private String bankNm;
        private String bankBrchCd;
        private String bankAddr;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Param
    {
        @Length(max = 5, message = "max = 5")
        private String bankCd;
        @Length(max = 40, message = "max = 40")
        private String bankNm;
        @Length(max = 10, message = "max = 10")
        private String bankBrchCd;
        @Length(max = 255, message = "max = 255")
        private String bankAddr;
    }

}
