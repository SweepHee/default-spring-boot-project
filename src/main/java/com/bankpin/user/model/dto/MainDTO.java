package com.bankpin.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class MainDTO
{
    @Data
    public static class FastItem
    {
        private String custNm;
        private String custGender;
        private String custBirth;
        private String lnGbcd;
        private String lnUseGbcd;
        private Integer avlCnt;
        private String lnReqNo;
        private String fintecOrgMngno;
        private String lstLnRate;
        private String lstLnLmtAmt;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Param
    {
        @Length(max = 1, message = "max = 1")
        private String lnGbcd;
        @Length(max = 1, message = "max = 1")
        private String lnReqGbcd;
        @Length(max = 1, message = "max = 1")
        private String lnRsltStcd;
    }


    public static class LoanItem
    {
        private String lnReqNo;
        private String fintecOrgMngno;
        private String bankCd;
        private String bankBrchCd;
        private String lnPrdtCd;
        private String lnPrdtNm;
        private BigDecimal lstLnLmtAmt;
        private String lstLnRate;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class LoanParam
    {
        @Length(max = 1, message = "max = 1")
        private String lnGbcd;
        @Length(max = 1, message = "max = 1")
        private String lnReqGbcd;
        @Length(max = 1, message = "max = 1")
        private String lnRsltStcd;
    }

}
