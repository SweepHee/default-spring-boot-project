package com.bankpin.user.cust.model.dto;

import lombok.Builder;
import lombok.Data;

public class CustAuthDtlDTO
{
    @Data
    @Builder
    public static class Create
    {
        private String custCiNo;
        private String lnReqNo;
        private String selfAuthMethCd;
        private String selfAgreeDttm;
        private String selfAgreeYn;
        private String teleCd;
        private String authOrgUnicd;
        private String custCphoneNo;
    }

    @Data
    public static class Detail
    {
        private String custCiNo;
        private String lnReqNo;
        private String selfAuthMethCd;
        private String selfAgreeDttm;
        private String selfAgreeYn;
        private String teleCd;
        private String authOrgUnicd;
        private String custCphoneNo;
    }

    @Data
    public static class Item
    {
        private String custCiNo;
        private String lnReqNo;
        private String selfAuthMethCd;
        private String selfAgreeDttm;
        private String selfAgreeYn;
        private String teleCd;
        private String authOrgUnicd;
        private String custCphoneNo;
    }

    @Data
    public static class Param
    {
        private String custCiNo;
        private String lnReqNo;
        private String selfAuthMethCd;
        private String selfAgreeDttm;
        private String selfAgreeYn;
        private String teleCd;
        private String authOrgUnicd;
        private String custCphoneNo;
    }

}
