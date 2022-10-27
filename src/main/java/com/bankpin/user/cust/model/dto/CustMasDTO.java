package com.bankpin.user.cust.model.dto;

import lombok.Data;

public class CustMasDTO
{
    @Data
    public static class Create
    {
        private String custCiNo;
        private String custSimplePwd;
        private String custId;
        private String custNm;
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
        private String custId;
        private String custNm;
        private String custAuthCd;
        private Boolean custActvGbcd;
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
        private String custAuthCd;
        private String custEmail;
        private String custGender;
    }

}
