package com.bankpin.user.cust.model.dto;

import lombok.Data;

public class CustMasDTO
{
    @Data
    public static class Create
    {
        private String custCiNo;
        private String custSimplePwd;
        private String custNm;
        private String custAuthCd;
        private Boolean custActvGbcd;
    }

    @Data
    public static class Detail
    {
        private String custCiNo;
        private String custSimplePwd;
        private String custNm;
        private String custAuthCd;
    }

    @Data
    public static class Item
    {
        private String custCiNo;
        private String custNm;
        private String custAuthCd;
    }

}
