package com.bankpin.user.inq.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InqrsltLstDTO
{
    @Data
    public static class Create
    {
        @NotBlank(message = "not null")
        @Length(max = 14, message = "max = 14")
        private String lnReqNo;
        @NotBlank(message = "not null")
        @Length(max = 20, message = "max = 20")
        private String fintecOrgMngno;
        @Length(max = 5, message = "max = 5")
        private String bankCd;
        @Length(max = 10, message = "max = 10")
        private String bankBrchCd;
        @Length(max = 1, message = "max = 1")
        private String lnReqGbcd;
        @Length(max = 16, message = "max = 16")
        private String lnPrdtCd;
        @Length(max = 1, message = "max = 1")
        private String lnPrdtNm;
        @Length(max = 16, message = "max = 16")
        private String lnRsltStcd;
        @Length(max = 50, message = "max = 50")
        private String nrsltRsnCntn;
        @Positive
        @Digits(integer = 15, fraction = 0, message = "max = 15")
        private String lstLnLmtAmt;
        @Positive
        @Digits(integer = 3, fraction = 5, message = "max = 3,5")
        private BigDecimal lstLnRate;
        @Length(max = 3, message = "max = 3")
        private String lstLnTermMm;
        @Length(max = 1, message = "max = 1")
        private String lnRtnMthCd;
        @Length(max = 1, message = "max = 1")
        private String lnRateKindGbcd;
        @Positive
        @Digits(integer = 3, fraction = 5, message = "max = 3,5")
        private BigDecimal strdRate;
        @Positive
        @Digits(integer = 3, fraction = 5, message = "max = 3,5")
        private BigDecimal applyRate;
        private Boolean priRateYn;
        @Length(max = 100, message = "max = 100")
        private String priRateRsnCntn;
        @Positive
        @Digits(integer = 15, fraction = 0, message = "max = 15")
        private BigDecimal priLmtAmt;
        @Positive
        @Digits(integer = 3, fraction = 5, message = "max = 3,5")
        private BigDecimal priRate;
        private LocalDateTime lnValidDttm;
        @Length(max = 14, message = "max = 14")
        private String strLnValidDttm;
        @Length(max = 3, message = "max = 3")
        private String lnRateCycleCd;
        @Positive
        @Digits(integer = 3, fraction = 5, message = "max = 3,5")
        private BigDecimal lnMidrtnFeeRate;
        private Boolean lnMidrtnFeeYn;
        private Boolean endAllrtnAblYn;
        private Boolean imDepotAblYn;
        private Boolean holiDepotAbleYn;
        private Boolean prinEqRtnAblYn;
        private Boolean prinitrEqRtnAblYn;
        private Boolean minusBbookAblYn;
    }

    @Data
    public static class Detail
    {
        private String lnReqNo;
        private String fintecOrgMngno;
        private String bankCd;
        private String bankBrchCd;
        private String lnReqGbcd;
        private String lnPrdtCd;
        private String lnPrdtNm;
        private String lnRsltStcd;
        private String nrsltRsnCntn;
        private String lstLnLmtAmt;
        private BigDecimal lstLnRate;
        private String lstLnTermMm;
        private String lnRtnMthCd;
        private String lnRateKindGbcd;
        private BigDecimal strdRate;
        private BigDecimal applyRate;
        private Boolean priRateYn;
        private String priRateRsnCntn;
        private BigDecimal priLmtAmt;
        private BigDecimal priRate;
        private LocalDateTime lnValidDttm;
        private String lnRateCycleCd;
        private BigDecimal lnMidrtnFeeRate;
        private Boolean lnMidrtnFeeYn;
        private Boolean endAllrtnAblYn;
        private Boolean imDepotAblYn;
        private Boolean holiDepotAbleYn;
        private Boolean prinEqRtnAblYn;
        private Boolean prinitrEqRtnAblYn;
        private Boolean minusBbookAblYn;
    }

    @Data
    public static class Item
    {
        private String lnReqNo;
        private String fintecOrgMngno;
        private String bankCd;
        private String bankBrchCd;
        private String lnReqGbcd;
        private String lnPrdtCd;
        private String lnPrdtNm;
        private String lnRsltStcd;
        private String nrsltRsnCntn;
        private String lstLnLmtAmt;
        private BigDecimal lstLnRate;
        private String lstLnTermMm;
        private String lnRtnMthCd;
        private String lnRateKindGbcd;
        private BigDecimal strdRate;
        private BigDecimal applyRate;
        private Boolean priRateYn;
        private String priRateRsnCntn;
        private BigDecimal priLmtAmt;
        private BigDecimal priRate;
        private LocalDateTime lnValidDttm;
        private String lnRateCycleCd;
        private BigDecimal lnMidrtnFeeRate;
        private Boolean lnMidrtnFeeYn;
        private Boolean endAllrtnAblYn;
        private Boolean imDepotAblYn;
        private Boolean holiDepotAbleYn;
        private Boolean prinEqRtnAblYn;
        private Boolean prinitrEqRtnAblYn;
        private Boolean minusBbookAblYn;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Progress
    {
        private int progress;
        private int done;
        private int result;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Param
    {
        @Length(max = 5, message = "max = 5")
        private String bankCd;
        @Length(max = 32, message = "max = 32")
        private String custCiNo;
        @Length(max = 14, message = "max = 14")
        private String lnReqNo;
        @Length(max = 20, message = "max = 20")
        private String fintecOrgMngno;
        @Length(max = 1, message = "max = 1")
        private String lnReqGbcd;
        @Length(max = 1, message = "max = 1")
        private String lnGbcd;
        @Length(max = 8, message = "max = 8")
        private String sortOrder;
    }

}
